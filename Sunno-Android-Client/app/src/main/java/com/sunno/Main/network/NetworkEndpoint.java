package com.sunno.Main.network;

import com.sunno.Main.Model.Request.PlayRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkEndpoint {

    @POST("play")
    Call<String> getUrl(@Body PlayRequest request);
}
