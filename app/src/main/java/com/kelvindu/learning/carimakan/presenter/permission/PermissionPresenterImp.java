package com.kelvindu.learning.carimakan.presenter.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.kelvindu.learning.carimakan.view.main.MainView;

/**
 * This presenter is where all of the permission logic is implemented in this application.
 * the only permission that are required by the user are ACCESS_FINE_LOCATION and ACCESS_COARSE_LOCATION.
 *
 * maybe in the real use, this app only need one of two of the permission to run.
 * but to avoid any hassle of debugging I decided to request for both of the permission.
 *
 * Created by KelvinDu on 5/21/2017.
 */

public class PermissionPresenterImp implements PermissionPresenter {
    private static final int REQUEST_FINE_LOCATION = 10;
    private static final int REQUEST_COARSE_LOCATION = 20;
    MainView view;

    public PermissionPresenterImp(MainView view) {
        this.view = view;
    }

    /**
     * This method gives permission for the Location to uses the phone's Location system.
     *
     * this method is called in GoogleApiPresenter. but it needs to know the view Activity that request the permission.
     * therefore it also have to interact with the MainView although not directly.
     *
     * @param mGoogleApiClient is the GoogleApiClient to generates the last location to the user.
     * @return last known location of the user.
     * */
    @Override
    public Location onLocationPermissionResults(GoogleApiClient mGoogleApiClient) {
        if (ActivityCompat.checkSelfPermission(view.getViewActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            if(ActivityCompat.shouldShowRequestPermissionRationale(view.getViewActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(view.getViewActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_FINE_LOCATION);
            }
        }
        if(ActivityCompat.checkSelfPermission(view.getViewActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(view.getViewActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(view.getViewActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_COARSE_LOCATION);
            }
        }
        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        return l;
    }
}
