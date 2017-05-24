package com.kelvindu.learning.carimakan.view.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelvindu.learning.carimakan.R;
import com.kelvindu.learning.carimakan.base.BaseActivity;
import com.kelvindu.learning.carimakan.model.zomato.Restaurant;
import com.kelvindu.learning.carimakan.presenter.detail.DetailPresenter;
import com.kelvindu.learning.carimakan.presenter.detail.DetailPresenterImp;

import butterknife.BindView;
import io.realm.Realm;

/**
 * This activity displays the restaurant information in more detail.
 * the implementation of the DetailView methods are implemented in here.
 * */
public class DetailActivity extends BaseActivity implements DetailView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.header_image)
    ImageView headerImage;

    @BindView(R.id.menu_retaurant)
    CardView menuCardView;
    @BindView(R.id.photo_restaurant)
    CardView photoCardView;
    @BindView(R.id.navigate_restaurant)
    CardView mapCardView;

    @BindView(R.id.rating_restaurant)
    TextView ratingRestaurant;
    @BindView(R.id.cuisines_restaurant)
    TextView cuisinesRestaurant;
    @BindView(R.id.price_restaurant)
    TextView priceRestaurant;
    @BindView(R.id.address_restaurant)
    TextView addressRestaurant;

    Realm realm = Realm.getDefaultInstance();
    DetailPresenter detailPresenter;

    Restaurant restaurant;
    private String lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailPresenter = new DetailPresenterImp(this);
        lat = getIntent().getExtras().getString("lat");
        lon = getIntent().getExtras().getString("lon");
    }

    /**
     * This Activity is actually very simple and I think there's no need for extracting it into 1 method.
     * so overriding the onStart is enough.
     *
     * */
    @Override
    protected void onStart() {
        super.onStart();

        restaurant = realm.where(Restaurant.class)
                .equalTo("id",getIntent().getExtras().getString("id")).findFirst();

        getSupportActionBar().setTitle(restaurant.getName());
        detailPresenter.bindImage(restaurant.getThumb(),this,headerImage);

        priceRestaurant.setText(detailPresenter.convertPrice(restaurant.getAverage_cost_for_two()));
        cuisinesRestaurant.setText(restaurant.getCuisines());
        addressRestaurant.setText(restaurant.getLocation().getAddress());
        ratingRestaurant.setText(restaurant.getUser_rating().getAggregate_rating()+"/5.0");

        menuCardView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.getMenu_url()));
            startActivity(intent);
        });
        photoCardView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.getPhotos_url()));
            startActivity(intent);
        });
        mapCardView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?"  +
                                    "&daddr=" + lat + ","
                                    + lon));
            startActivity(intent);
        });
    }
}
