package com.sunno.AuthModule.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sunno.URLConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {

    private static Retrofit INSTANCE = null;

    public static Retrofit getRetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(URLConstants.ACCOUNT_SERVICE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return INSTANCE;
    }

}
