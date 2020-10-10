package com.sunno.Main.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoint {
    @GET("metadata")
    Call<MetaDataModel> getMetaData();
}
