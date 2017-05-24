package com.kelvindu.learning.carimakan.presenter.permission;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * The presenter interactor for PermissionPresenter.
 * all of the methods that communicating with another component defined here.
 *
 * Created by KelvinDu on 5/21/2017.
 */

public interface PermissionPresenter {
    Location onLocationPermissionResults(GoogleApiClient mGoogleApiClient);
}
