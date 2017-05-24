package com.kelvindu.learning.carimakan.presenter.main;

import com.kelvindu.learning.carimakan.model.main.MainModel;
import com.kelvindu.learning.carimakan.model.main.MainModelImp;
import com.kelvindu.learning.carimakan.model.zomato.Nearby_restaurant;
import com.kelvindu.learning.carimakan.view.main.MainView;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This class is the implementation of the methods declared in the MainPresenter interface.
 *
 * Presenter class is where all of the application logic defined.
 * noticed that this class does not relied on the Activity context like fragment.
 *
 * it's because a good presenter must be separated completely from the view and the model.
 * we only interact with models and view through their interactor (java interface class).
 *
 * Created by KelvinDu on 5/19/2017.
 */

public class MainPresenterImp implements MainPresenter {

    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";

    private static String lat, lon;

    MainModel model;
    MainView view;
    Realm realm = Realm.getDefaultInstance();

    public MainPresenterImp(MainView view) {
        model = new MainModelImp();
        this.view = view;
    }

    /**
     * This method is to retrieve the phone's latitude and longitude from the Google Api Client.
     * and initialize it here, for the request.
     * this method is called in GoogleApiPresenter.
     *
     * @param latitude is the device's latitude.
     * @param longitude is the device's longitude.
     * */
    @Override
    public void setLocation(String latitude, String longitude) {
        lat = latitude;
        lon = longitude;
    }

    /**
     * This method is where the network request is executed in the app.
     * we're not executing the request in the view class instead in here. makes the view class, is logic free.
     *
     * @return retrofit json request which later will be parsed as Gson object and returned in it's callback.
     * */
    @Override
    public Observable<MainModelImp> getResults() {
        return model.build().getNearbyResult(lat, lon);
    }

    /**
     * This method caches the request that are successfully retrieved from retrofit.
     * so bywhen there's no internet users won't left off with blank screen until they can retrieve the updated data.
     *
     * @param res is the results from the requests in form of MainModel.
     * */
    @Override
    public void realmTransaction(MainModelImp res) {
        realm.executeTransaction(realmT -> {
            RealmResults<MainModelImp> current = getRealmResults(res.getLocation().getLatitude(), res.getLocation().getLongitude());
            current.deleteAllFromRealm();
            MainModelImp cache = realmT.createObject(MainModelImp.class);
            cache.setLat(res.getLocation().getLatitude());
            cache.setLon(res.getLocation().getLongitude());
            for (Nearby_restaurant nearby : res.getNearby_restaurants()) {
                cache.getNearby_restaurants().add(nearby);
            }
        });

    }

    /**
     * This method retrieves the realm object that are cached in the phone. where the latitude and the longitude
     * is the same as the current phone's latitude and longitude.
     *
     * @param lat is the corresponding latitude.
     * @param lon is the corresponding longitude.
     * @return RealmList of all MainModelImp object.
     * */
    @Override
    public RealmResults<MainModelImp> getRealmResults(String lat, String lon) {
        return realm.where(MainModelImp.class)
                .equalTo(LATITUDE, lat)
                .equalTo(LONGITUDE, lon)
                .findAll();
    }

    /**
     * This method is the getter for the latitude.
     * called on Search Presenter.
     * */
    @Override
    public String getLat() {
        return lat;
    }

    /**
     * This method is the getter for the longitude.
     * called on Search Presenter.
     * */
    @Override
    public String getLon() {
        return lon;
    }
}
