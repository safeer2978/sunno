package com.sunno;

import com.sunno.Model.Genre;
import com.sunno.Model.MetaDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/metadata")
    Call<MetaDataModel> getMetaData();
}
