package com.sunno.Main.Model;

import android.app.Application;
import android.util.Log;

import com.sunno.AuthModule.data.model.User;
import com.sunno.AuthModule.db.LoginDao;
import com.sunno.AuthModule.db.LoginDatabase;
import com.sunno.AuthModule.network.LoginEndpoints;
import com.sunno.AuthModule.network.LoginService;
import com.sunno.AuthModule.network.request.RefreshTokenRequest;
import com.sunno.AuthModule.network.response.LoginResponse;
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

    LoginDao loginDao;

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

        loginDao = LoginDatabase.getDatabase(application.getApplicationContext()).wordDao();
        accountsApi = LoginService.getRetrofitClient().create(LoginEndpoints.class);
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


    LoginEndpoints accountsApi;

    public void getNewAccessToken(){
        User user = loginDao.getUser().get(0);
        String refreshToken = user.getRefreshToken();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(refreshToken);
        accountsApi.refreshToken(refreshTokenRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                assert loginResponse != null;
                final User user = new User(loginDao.getUser().get(0).getEmail(), loginResponse.getAccessToken(),
                        loginResponse.getRefreshToken(),
                        loginResponse.getTokenType(),
                        loginResponse.getExpiresInMsec());
                System.out.println(user.getAccessToken());
                loginDao.dropTable(user);
                loginDao.insertUser(user);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
