
package com.kelvindu.learning.carimakan.model.main;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kelvindu.learning.carimakan.BuildConfig;
import com.kelvindu.learning.carimakan.model.zomato.Location;
import com.kelvindu.learning.carimakan.model.zomato.Nearby_restaurant;
import com.kelvindu.learning.carimakan.utils.OkHttpTime;
import com.kelvindu.learning.carimakan.utils.ZomatoService;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Actually this is also a generated pojo class from http://www.jsonschema2pojo.org/
 * with a little tweak. such as the added lat and lon string as for the Realm queries.
 *
 * because we uses Realm to cache all of the information we fetch from network.
 * it requires that ALL of the models that interacting with Realm transaction to extends from RealmObject
 *
 * Created by KelvinDu on 5/23/2017.
 * */
public class MainModelImp extends RealmObject implements MainModel {

    private String lat;
    private String lon;

    private Location location;
    private String link;
    private RealmList<Nearby_restaurant> nearby_restaurants = null;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public RealmList<Nearby_restaurant> getNearby_restaurants() {
        return nearby_restaurants;
    }

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

    public void setNearby_restaurants(RealmList<Nearby_restaurant> nearby_restaurants) {
        this.nearby_restaurants = nearby_restaurants;
    }

    /**
     * This method creates the request that will be sent through Retrofit services.
     *
     * @return new retrofit request with defined factories, client, and the build process.
     * */
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
