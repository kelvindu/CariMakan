package com.kelvindu.learning.carimakan.view.main;

import android.app.Activity;

import com.kelvindu.learning.carimakan.model.main.MainModelImp;
import com.kelvindu.learning.carimakan.model.search.SearchModelImp;

/**
 * This is the interactor for MainActivity.
 * Because in MVP design pattern, MainActivity is restricted to access the model directly.
 * so in order to maintain the communication between view and model class, we need both interface to be interacting
 * inside a presenter class.
 * this is so that the view class is totally separated from application logic.
 *
 * all of the methods defined in this calls will be interacting with presenter.
 *
 * Created by KelvinDu on 5/19/2017.
 */

public interface MainView {
    void onSuccess(MainModelImp results);
    void onSearchSuccess(SearchModelImp results);
    void onError(Throwable err);
    Activity getViewActivity();
    void subscribeData();
}
