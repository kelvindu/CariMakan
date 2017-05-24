package com.kelvindu.learning.carimakan.utils;

import com.kelvindu.learning.carimakan.BuildConfig;
import com.kelvindu.learning.carimakan.model.main.MainModelImp;
import com.kelvindu.learning.carimakan.model.search.SearchModelImp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * This is where retrofit know which URI to be called during making a request to the server.
 *
 * Created by KelvinDu on 5/19/2017.
 */

public interface ZomatoService {

    /**
     * This method returns all of the json queries of nearby restaurant.
     * */
    @Headers("user-key: "+ BuildConfig.USER_KEY)
    @GET("geocode")
    Observable<MainModelImp> getNearbyResult(@Query("lat") String latitude, @Query("lon") String longitude);

    /**
     * This method returns all of the json queries from user's search.
     * */
    @Headers("user-key: "+BuildConfig.USER_KEY)
    @GET("search")
    Observable<SearchModelImp> searchResult(@Query("q") String query,@Query("lat") String latitude, @Query("lon") String longitude);
}
