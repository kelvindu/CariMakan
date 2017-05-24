package com.kelvindu.learning.carimakan.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * This class defines the timeout duration for the request.
 * Using OkHttpClient.
 *
 * Created by yao on 14/05/17.
 */

public class OkHttpTime {
    public static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)//the connection will timeout in 10 seconds.
            .readTimeout(6000L,TimeUnit.MILLISECONDS)
            .build();
}
