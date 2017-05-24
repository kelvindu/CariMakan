package com.kelvindu.learning.carimakan.model.search;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kelvindu.learning.carimakan.BuildConfig;
import com.kelvindu.learning.carimakan.model.zomato.Nearby_restaurant;
import com.kelvindu.learning.carimakan.utils.OkHttpTime;
import com.kelvindu.learning.carimakan.utils.ZomatoService;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Yes, this is too a generated pojo class from http://www.jsonschema2pojo.org/
 * But it shares it's object with the main, so only this class that are generated with it's tweak.
 *
 * because we uses Realm to cache all of the information we fetch from network.
 * it requires that ALL of the models that interacting with Realm transaction to extends from RealmObject
 *
 * Created by KelvinDu on 5/23/2017.
 */
public class SearchModelImp implements SearchModel {

    private String lat, lon;
    private Integer results_found;
    private Integer results_start;
    private Integer results_shown;
    private RealmList<Nearby_restaurant> restaurants = null;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Integer getResults_found() {
        return results_found;
    }

    public void setResults_found(Integer results_found) {
        this.results_found = results_found;
    }

    public Integer getResults_start() {
        return results_start;
    }

    public void setResults_start(Integer results_start) {
        this.results_start = results_start;
    }

    public Integer getResults_shown() {
        return results_shown;
    }

    public void setResults_shown(Integer results_shown) {
        this.results_shown = results_shown;
    }

    public RealmList<Nearby_restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RealmList<Nearby_restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    //look at the MainModelImp class to see the functions from this method.
    @Override
    public ZomatoService build() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.URI)
                .client(OkHttpTime.client)
                .build().create(ZomatoService.class);
    }
}
