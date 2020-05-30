package com.app.eazydigi.api;

import com.app.eazydigi.BuildConfig;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AppApi {

    final static String BASE_URL = "http://serp.eazydigi.com/apiproject/api/";

    protected AppApi() {
    }

    public static APIInterface initialize() {
        APIInterface api = null;
        try {
            OkHttpClient client = logging();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();

            api = retrofit.create(APIInterface.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return api;
    }

    public static OkHttpClient logging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;

    }

}
