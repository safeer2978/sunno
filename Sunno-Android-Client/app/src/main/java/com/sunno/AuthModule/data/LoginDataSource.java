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

    static LoginEndpoints api;

    LoginDao dao;

    public LoginDataSource(@NonNull Application application){
        super();
        api = LoginService.getRetrofitClient().create(LoginEndpoints.class);
        dao = LoginDatabase.getDatabase(application).wordDao();
    }

    public Result<User> login(final String username, String password) {

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
                        System.out.println(user.getAccessToken());
                        dao.dropTable(user);
                        dao.insertUser(user);
                        System.out.println("Reached Here end of dao");
                        System.out.println(dao.getUser().get(0).getAccessToken()+"OTHER");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                   System.out.println("Reached Failure Here");
                }
            });


            System.out.println("Reached Here");
            return new Result.Success<User>(dao.getUser().get(0));
        } catch (Exception e) {
            return new Result.Error(new Exception(""));
        }
    }
    static boolean flag;
    public static Boolean signUp(SignUpRequest signUpRequest){
        try {

            api.onSignUp(signUpRequest).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    Log.i(TAG, "API connected:"+response.code());

                    LoginDataSource.flag=response.code()==201;
                    System.out.println("1 status : " +flag);
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {

                }
            });

            System.out.println("status : " +flag);
            return flag;
        } catch (Exception e) {
            System.out.println("status : EXCEPTION");
            return false;
        }
    }


    public void logout() {
        dao.dropTable(dao.getUser().get(0));
    }
}