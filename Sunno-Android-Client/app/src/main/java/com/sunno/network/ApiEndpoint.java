package com.sunno.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("metadata")
    Call<MetaDataModel> getMetaData();
}
