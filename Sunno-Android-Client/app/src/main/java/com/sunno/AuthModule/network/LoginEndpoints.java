package com.sunno.AuthModule.network;

import com.sunno.AuthModule.network.request.LoginRequest;
import com.sunno.AuthModule.network.request.RefreshTokenRequest;
import com.sunno.AuthModule.network.request.SignUpRequest;
import com.sunno.AuthModule.network.response.LoginResponse;
import com.sunno.AuthModule.network.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginEndpoints {
    @POST("sign_in")
    Call<LoginResponse> onLogin(@Body LoginRequest request);

    @POST("sign_up")
    Call<SignUpResponse> onSignUp(@Body SignUpRequest request);

    @POST("refreshToken")
    Call<LoginResponse> refreshToken(@Body RefreshTokenRequest refreshToken);

}
