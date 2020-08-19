package com.sunno;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.sunno.Model.Genre;
import com.sunno.network.ApiEndpoint;
import com.sunno.network.ApiService;
import com.sunno.network.MetaDataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiEndpoint api;

    private static Repository REPOSITORY;

    public static Repository getInstance(Application application) {

        if (REPOSITORY == null) {
            REPOSITORY = new Repository(application);
        }
        return REPOSITORY;
    }

    List<Genre> genreListFromMApi = new ArrayList<>();

    Repository(Application application) {
        api = ApiService.getRetrofitClient().create(ApiEndpoint.class);
        api.getMetaData()
                .enqueue(new Callback<MetaDataModel>() {
                    @Override
                    public void onResponse(Call<MetaDataModel> call, Response<MetaDataModel> response) {
                        if (!response.isSuccessful()) {
                            Log.d("HomeFragment2", "onFailure: " + response.code());
                            return;
                        }
                        MetaDataModel metaDataModelFromApi = response.body();

                        System.out.println(metaDataModelFromApi);
                        genreListFromMApi = metaDataModelFromApi.getGenre();
                    }

                    @Override
                    public void onFailure(Call<MetaDataModel> call, Throwable t) {

                    }
                });

    }


    public List<Genre> getGenreList(){
        return genreListFromMApi;
    }

}
