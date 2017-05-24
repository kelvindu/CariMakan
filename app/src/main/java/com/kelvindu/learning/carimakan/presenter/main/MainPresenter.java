package com.kelvindu.learning.carimakan.presenter.main;

import com.kelvindu.learning.carimakan.model.main.MainModelImp;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * The presenter interactor for MainPresenter.
 * all of the methods that communicating with another component defined here.
 *
 * Created by KelvinDu on 5/19/2017.
 */

public interface MainPresenter {
    Observable<MainModelImp> getResults();
    RealmResults<MainModelImp> getRealmResults(String lat, String lon);
    String getLat();
    String getLon();
    void setLocation(String latitude, String longitude);
    void realmTransaction(MainModelImp res);
}
