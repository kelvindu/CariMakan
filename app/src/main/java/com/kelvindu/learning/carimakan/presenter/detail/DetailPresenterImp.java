package com.kelvindu.learning.carimakan.presenter.detail;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kelvindu.learning.carimakan.R;
import com.kelvindu.learning.carimakan.view.detail.DetailView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class is the implement of the methods declared in the DetailPresenter.
 *
 * Created by KelvinDu on 5/24/2017.
 */
public class DetailPresenterImp implements DetailPresenter {

    DetailView view;

    /**
     * This constructor initialize the view that interacts with this presenter.
     * @param view is the view that interact with this presenter in this case is detail activity.
     * */
    public DetailPresenterImp(DetailView view) {
        this.view = view;
    }

    /**
     * This method binds the restaurant thumbnail images into the cards.
     *
     * @param url is the link to request the images.
     * @param context is the activity that does the request.
     * @param imageView is the target image view to bind with glide.
     * */
    @Override
    public void bindImage(String url, Context context, ImageView imageView) {
        Glide.with(context).load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.blank_screen)
                .skipMemoryCache(false)
                .into(imageView);
    }

    /**
     * This method convert the double values of price into proper currency format string.
     *
     * @param price is the floating number that will be converted into currency string.
     * @return the price with currency format in this case IDR.
     * */
    @Override
    public String convertPrice(double price) {
        Locale indo = new Locale("id", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indo);
        return numberFormat.format(price);
    }
}
