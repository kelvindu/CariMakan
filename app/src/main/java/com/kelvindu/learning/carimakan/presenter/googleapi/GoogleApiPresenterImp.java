package com.kelvindu.learning.carimakan.presenter.googleapi;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.kelvindu.learning.carimakan.presenter.main.MainPresenter;
import com.kelvindu.learning.carimakan.presenter.permission.PermissionPresenter;
import com.kelvindu.learning.carimakan.presenter.permission.PermissionPresenterImp;
import com.kelvindu.learning.carimakan.view.main.MainView;

/**
 * This presenter is for all the use of GoogleApi services.
 * This class also interacts with the MainView, but also interacts with 2 other presenter that is permission and mainPresenter.
 *
 * Created by KelvinDu on 5/22/2017.
 */

public class GoogleApiPresenterImp implements GoogleApiPresenter, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    MainPresenter mainPresenter;
    PermissionPresenter permission;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    MainView view;


    public GoogleApiPresenterImp(MainPresenter mainPresenter, MainView view) {
        this.mainPresenter = mainPresenter;
        this.view = view;
        permission = new PermissionPresenterImp(view);
    }

    /**
     * This method initialize the Google API client for the app.
     * this method is called on MainActivity.
     * */
    @Override
    public void setGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(view.getViewActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * The getter method for the Google Api Client Object.
     *
     * @return the Google Api Client Object.
     * */
    @Override
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    /**
     * The callback method that will fired after GoogleApiClient connection is successful.
     *
     * @param bundle is the arguments that could be passed, but in this case nothing is passed into the bundle.
     * */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocation = permission.onLocationPermissionResults(mGoogleApiClient);
        if(mLocation != null){
            mainPresenter.setLocation(String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()));
        } else {
            Log.d("MainPresenter", "No location is set");
        }
        view.subscribeData();
    }

    /**
     * The callback method when GoogleApiClient connection is pending.
     * */
    @Override
    public void onConnectionSuspended(int i) {
        Log.d("MainPresenter", "Connection Suspended");
    }

    /**
     * The callback method when GoogleApiClient connection is failed.
     * */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { Log.d("MainPresenter", "Connection failed"); }
}
