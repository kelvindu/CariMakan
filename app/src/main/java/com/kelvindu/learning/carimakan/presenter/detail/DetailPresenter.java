package com.kelvindu.learning.carimakan.presenter.detail;

import android.content.Context;
import android.widget.ImageView;

/**
 * The presenter interactor for DetailPresenter.
 * all of the methods that communicating with another component defined here.
 *
 * Created by KelvinDu on 5/24/2017.
 */

public interface DetailPresenter {
    void bindImage(String url, Context context, ImageView imageView);
    String convertPrice(double price);
}
