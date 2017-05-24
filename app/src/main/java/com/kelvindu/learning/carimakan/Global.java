package com.kelvindu.learning.carimakan;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Realm configuration for whole application.
 *
 * */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().
                deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
