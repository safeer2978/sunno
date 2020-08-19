package com.sunno.AuthModule.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sunno.AuthModule.data.model.User;
import com.sunno.AuthModule.db.LoginDao;
import com.sunno.AuthModule.db.LoginDatabase;
import com.sunno.AuthModule.network.LoginEndpoints;
import com.sunno.AuthModule.network.LoginService;
import com.sunno.AuthModule.network.request.LoginRequest;
import com.sunno.AuthModule.network.request.SignUpRequest;
import com.sunno.AuthModule.network.response.LoginResponse;
import com.sunno.AuthModule.network.response.SignUpResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final String TAG = "LOGIN_DATA_STORE";

    LoginEndpoints api;

    LoginDao dao;

    public LoginDataSource(@NonNull Application application){
        super();
        api = LoginService.getRetrofitClient().create(LoginEndpoints.class);
        dao = LoginDatabase.getDatabase(application).wordDao();
    }

    public Result login(final String username, String password) {

        try {
            final LoginRequest request = new LoginRequest(username, password);

            api.onLogin(request).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.i(TAG, "API connected "+response.code());
                    if (response.code() == 200) {
                        LoginResponse loginResponse = response.body();
                        assert loginResponse != null;
                        System.out.println(loginResponse.toString());
                        final User user = new User(username, loginResponse.getAccessToken(),
                                loginResponse.getRefreshToken(),
                                loginResponse.getTokenType(),
                                loginResponse.getExpiresInMsec());
                        System.out.println(user.tokenType);
                        Thread t =
                                new Thread() {
                                    @Override
                                    public void run() {
                                        dao.insertUser(user);
                                    }
                                };
                        t.start();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });


            return new Result.Success<User>(dao.getUser());
        } catch (Exception e) {
            return new Result.Error(new Exception(""));
        }
    }

    public Boolean signUp(SignUpRequest signUpRequest){
        try {

            final String[] message = new String[2];
            api.onSignUp(signUpRequest).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    Log.i(TAG, "API connected");
                    SignUpResponse signUpResponse = response.body();
                    message[0] = signUpResponse.getMessage();
                    message[1] = signUpResponse.getStatus().toString();
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {

                }
            });
            System.out.println(message[0]);
            return message[1].equals("true");
        } catch (Exception e) {
            return false;
        }
    }


    public void logout() {
        dao.dropTable(dao.getUser());
    }
}