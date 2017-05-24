package com.kelvindu.learning.carimakan.presenter.googleapi;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * The presenter interactor for GoogleApi.
 * all of the methods that communicating with another component defined here.
 *
 * Created by KelvinDu on 5/22/2017.
 */

public interface GoogleApiPresenter {
    void setGoogleApiClient();
    GoogleApiClient getGoogleApiClient();
}
