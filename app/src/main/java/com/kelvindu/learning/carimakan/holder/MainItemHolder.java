package com.kelvindu.learning.carimakan.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.kelvindu.learning.carimakan.R;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class is probably is the view holder for the main view.
 *
 * Created by KelvinDu on 5/21/2017.
 */

public class MainItemHolder extends RecyclerView.ViewHolder {

    //these are the views that are making one restaurant card.
    @BindView(R.id.name_restaurant)
    TextView resName;
    @BindView(R.id.user_rating)
    TextView resRating;
    @BindView(R.id.address_restaurant)
    TextView resAddress;
    @BindView(R.id.price_restaurant)
    TextView resPrice;

    @BindView(R.id.iv_restaurant)
    ImageView resImage;

    @BindView(R.id.card_restaurant)
    CardView cardRestaurant;

    /**
     * The constructor where the view component is bind through ButterKnife.
     *
     * @param itemView is the view on which the component will be bind.
     * */
    public MainItemHolder(View itemView) {
        super(itemView);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this,itemView);
    }

    /**
     * This method is the getters for the restaurant card view.
     * @return the cardview of the restaurant.
     * */
    public CardView getCardRestaurant(){
        return cardRestaurant;
    }

    /**
     * This method binds all of the text information into the cards.
     *
     * @param name is the name of the restaurant.
     * @param address is the location of the restaurant.
     * @param userRating is the ratings 0 to 5 that user give for the restaurant.
     * */
    public void bind(String name, String address, String userRating){
        resName.setText(name);
        resAddress.setText(address);
        resRating.setText(userRating);
    }

    /**
     * This method binds the price average for two people spends.
     *
     * @param price is the decimal number that will be converted to represent the  price in IDR.
     * */
    public void bindPrice(double price) {
        Locale indo = new Locale("id", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indo);
        String c = numberFormat.format(price);
        resPrice.setText("Approx. "+ c);
    }

    /**
     * This method binds the restaurant thumbnail images into the cards.
     *
     * @param url is the link to request the images.
     * @param context is the activity that does the request.
     * */
    public void bindImage(String url, Context context){
        Glide.with(context).load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.blank_screen)
                .skipMemoryCache(false)
                .into(resImage);
    }

}
