package com.kelvindu.learning.carimakan.presenter.search;

import com.kelvindu.learning.carimakan.model.main.MainModelImp;
import com.kelvindu.learning.carimakan.model.search.SearchModel;
import com.kelvindu.learning.carimakan.model.search.SearchModelImp;
import com.kelvindu.learning.carimakan.model.zomato.Nearby_restaurant;
import com.kelvindu.learning.carimakan.presenter.main.MainPresenter;
import com.kelvindu.learning.carimakan.view.main.MainView;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This Presenter is the implementation of the methods declared in the interactor.
 * and also handles every logic regarding searching.
 *
 * Created by KelvinDu on 5/23/2017.
 */

public class SearchPresenterImp implements SearchPresenter {
    private static final String LATITUDE = "lat",
            LONGITUDE="lon";

    MainView view;
    MainPresenter mainPresenter;
    SearchModel model;

    Realm realm = Realm.getDefaultInstance();
    private static String lat, lon;

    public SearchPresenterImp(MainView view, MainPresenter mainPresenter) {
        model = new SearchModelImp();
        this.view = view;
        this.mainPresenter = mainPresenter;
        setLocation();
    }

    /**
     * This method is called during initialization.
     *
     * so by the time user do the request,
     * SearchPresenter don't need retrieve the latitude and the longitude of the phone.
     * */
    public void setLocation(){
        lat = mainPresenter.getLat();
        lon = mainPresenter.getLon();
    }

    /**
     * For this method please look at the MainPresenter getResult model.
     * this is the exact same method with added parameters.
     *
     * @param query is the search query string that entered by the user.
     * @return retrofit json request which later will be parsed as Gson object and returned in it's callback.
     * */
    @Override
    public Observable<SearchModelImp> getResult(String query) {
        return model.build().searchResult(query,lat,lon);
    }

    /**
     * For this method please look at MainPresenter class.
     * why does not use the methods from MainPresenter then?
     * it's because of the parameter passed now is a different model than the main model.
     *
     * @param res is the results from the requests in form of SearchModel.
     */
    @Override
    public void realmTransaction(SearchModelImp res) {
        realm.executeTransaction(realmT -> {
            RealmResults<MainModelImp> current = mainPresenter.getRealmResults(lat , lon);
            current.deleteAllFromRealm();
            MainModelImp cache = realmT.createObject(MainModelImp.class);
            cache.setLat(lat);
            cache.setLon(lon);
            for (Nearby_restaurant nearby : res.getRestaurants()) {
                cache.getNearby_restaurants().add(nearby);
            }
        });
    }
}
