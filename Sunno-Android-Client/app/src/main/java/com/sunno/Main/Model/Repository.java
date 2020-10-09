package com.sunno.Main.Model;

import android.app.Application;
import android.util.Log;

import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.network.ApiEndpoint;
import com.sunno.Main.network.ApiService;
import com.sunno.Main.network.MetaDataModel;

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
    List<ArtistModel> artistListFromMApi = new ArrayList<>();
    List<AlbumModel> albumListFromMApi = new ArrayList<>();

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
                        Log.d("Repository", "Response: "+response.code());

                        MetaDataModel metaDataModelFromApi = response.body();

                        Log.d("Repository", "metaData: "+metaDataModelFromApi.toString());
                        assert metaDataModelFromApi != null;
                        genreListFromMApi = metaDataModelFromApi.getGenre();
                        Log.d("Repository", "Genre: "+genreListFromMApi);
                        artistListFromMApi=metaDataModelFromApi.getArtists();
                        albumListFromMApi=metaDataModelFromApi.getAlbums();
                        Log.d("Repository", "onResponse: "+artistListFromMApi.get(0).toString());

                    }

                    @Override
                    public void onFailure(Call<MetaDataModel> call, Throwable t) {
                        Log.d("Repository", "fAILED ");
                        try {
                            throw t;
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });
    }

    public List<Genre> getGenreList(){
        return genreListFromMApi;
    }

    public List<AlbumModel> getAlbumList(){
        return albumListFromMApi;
    }

    public List<ArtistModel> getArtistsList(){
        return artistListFromMApi;
    }

}
