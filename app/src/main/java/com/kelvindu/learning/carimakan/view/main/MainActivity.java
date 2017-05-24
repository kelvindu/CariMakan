package com.kelvindu.learning.carimakan.view.main;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.kelvindu.learning.carimakan.R;
import com.kelvindu.learning.carimakan.adapter.RecyclerAdapter;
import com.kelvindu.learning.carimakan.base.BaseActivity;
import com.kelvindu.learning.carimakan.holder.MainItemHolder;
import com.kelvindu.learning.carimakan.model.main.MainModelImp;
import com.kelvindu.learning.carimakan.model.search.SearchModelImp;
import com.kelvindu.learning.carimakan.model.zomato.Nearby_restaurant;
import com.kelvindu.learning.carimakan.presenter.googleapi.GoogleApiPresenter;
import com.kelvindu.learning.carimakan.presenter.googleapi.GoogleApiPresenterImp;
import com.kelvindu.learning.carimakan.presenter.main.MainPresenter;
import com.kelvindu.learning.carimakan.presenter.main.MainPresenterImp;
import com.kelvindu.learning.carimakan.presenter.search.SearchPresenter;
import com.kelvindu.learning.carimakan.presenter.search.SearchPresenterImp;
import com.kelvindu.learning.carimakan.view.detail.DetailActivity;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmList;

/**
 * Hi fellow developers, this might be the first source you're looking for.
 * So let me explains briefly what makes this app.
 *
 * this app uses MVP design pattern for the project structure (and hopefully done right)
 * and uses ReactiveX to fetch the request flow from the server.
 *
 * this app shows nearby restaurants from user location and let's user to search a restaurant near his place as well.
 * that's it, no weird things. this is just a simple app and it's actually easy to make for some people.
 *
 * NOTE: this app uses awesome zomato api, and I take no credit for any of the backend.
 *
 * if you have any question regarding the project you can post an issue at the git repo:
 * https://github.com/kelvindu/CariMakan
 *
 * -KD
 * */
public class MainActivity extends BaseActivity implements MainView, SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.refresh_container)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.rv_restaurant)
    RecyclerView mRecyclerView;
    RecyclerAdapter adapter;

    GoogleApiPresenter googleApiPresenter;
    MainPresenter mainPresenter;
    SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_main);
        setSupportActionBar(toolbar);

        refreshLayout.setRefreshing(true);
        mainPresenter = new MainPresenterImp(this);

        googleApiPresenter = new GoogleApiPresenterImp(mainPresenter, this);
        googleApiPresenter.setGoogleApiClient();

        //refresh listener is set to keep refreshing while executing fetchData.
        refreshLayout.setOnRefreshListener(()->{
            fetchData();
        });

    }

    //initially we're also doing fetch data because refresh layout is set refreshing initially but didn't call the fetchData
    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }

    /**
     * this method initialize the Google Api Client connection, and waiting for callbacks.
     * */
    public void fetchData(){
        googleApiPresenter.getGoogleApiClient().connect();
    }

    /**
     * This method execute the request from MainPresenter and returns 2 callback.
     *
     * note that the view isn't calling this method, it's because this method is called after Google Api Client
     * finishes retrieve the location and set the phone's latitude and longitude. which means the actual request is
     * called in GoogleApiPresenter after a connection is successful.
     *
     * Confusing? well I also don't know if it were a best practice or not, but at this point
     * this is how I implement the method.
     * */
    @Override
    public void subscribeData() {
        disposable = mainPresenter.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,this::onError);
    }

    /**
     * This is the callback after a subscriber successfully subscribe the data into the view.
     * This method caches the resonse body into realm object and bind the data into the recyclerview.
     *
     * @param results is the response body returned from retrofit callback.
     * */
    @Override
    public void onSuccess(MainModelImp results) {
        mainPresenter.realmTransaction(results);
        createList(results.getNearby_restaurants());
    }

    /**
     * Works similar with onSuccess, this is the callback method for searching.
     * Maybe there's a better way of doing this but I have no experience creating generic abstrac nor base classes.
     * so this all I could do.
     *
     * @param results is the response body returned from retrofit callback.
     * */
    @Override
    public void onSearchSuccess(SearchModelImp results) {
        searchPresenter.realmTransaction(results);
        createList(results.getRestaurants());
    }

    /**
     * this method is where the data is bind into the recycler view.
     * first the recyclerview set it's layout manager into linear vertical.
     *
     * then initialize the generic recycler adapter class. and passing the Nearby restaurant as the model to bind.
     * then interact with the view holder through overriding the bindView method where over there we hook the data into the view.
     * not to mention an onclick listener so that each view can take you to Detail Activity of their own respective restaurant.
     *
     * after all of that complete. the we will tell the view that was initially set to refreshing to stop refreshing.
     * and disconnect the GoogleApiClient since we have no use of keeping the connection alive at this point.
     * */
    public void createList(RealmList<Nearby_restaurant> results){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getViewActivity()));

        adapter = new RecyclerAdapter <Nearby_restaurant, MainItemHolder>(
                R.layout.restaurant_card,
                MainItemHolder.class, Nearby_restaurant.class,results) {

            @Override
            protected void bindView(MainItemHolder holder, Nearby_restaurant model, int position) {
                holder.bind(model.getRestaurant().getName(),
                        model.getRestaurant().getLocation().getAddress(),
                        model.getRestaurant().getUser_rating().getAggregate_rating());

                holder.bindPrice(model.getRestaurant().getAverage_cost_for_two());
                holder.bindImage(model.getRestaurant().getThumb(), getViewActivity());

                holder.getCardRestaurant().setOnClickListener(v -> {
                    v.getContext().startActivity(new Intent(v.getContext(), DetailActivity.class)
                            .putExtra("id", model.getRestaurant().getId())
                            .putExtra("lat",model.getRestaurant().getLocation().getLatitude())
                            .putExtra("lon",model.getRestaurant().getLocation().getLongitude()));
                });
            }

        };

        mRecyclerView.setAdapter(adapter);

        if(adapter != null){
            googleApiPresenter.getGoogleApiClient().disconnect();
            refreshLayout.setRefreshing(false);
        }
    }

    //the callbacks if the request is failed (hopefully will not get called)
    @Override
    public void onError(Throwable err) { Log.d(TAG,"uh oh, something is wrong here"); }

    /**
     * This method is the getter for the Main Activity.
     * Implemented this way so that presenter would not need to depend on the activity context.
     *
     * @return this activity context.
     * */
    @Override
    public Activity getViewActivity() {
        return this;
    }

    /**
     * The search icon is placed on the menu layout, and set to expand a SearchView when it clicked.
     *
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_food).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * These methods are the implementation for the search text listener.
     * this method execute the search request from the Search Presenter. and while doing all that,
     * set the refreshing layout into refreshing, well so that user know that we're trying to execute the search and
     * didn't left with any blank screen.
     *
     * @param query is the string input from user.
     * */
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchPresenter = new SearchPresenterImp(this, mainPresenter);
        refreshLayout.setRefreshing(true);
        disposable = searchPresenter.getResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchSuccess,this::onError);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
