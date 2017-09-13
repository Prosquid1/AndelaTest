package com.example.lg.andelatask.Utilities.REST;
import com.example.lg.andelatask.Utilities.Constants;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static String BASE_URL = Constants.API_URL;

    private static  OkHttpClient getRequestHeader() {

        return new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

    }


    /**
     * Get base re-usabele client for all API calls
     * @return Retrofit object
     */

    public static Retrofit getClient() {

       return new Retrofit.Builder()
                    .baseUrl(ApiClient.BASE_URL)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
