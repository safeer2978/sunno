package com.sunno.AuthModule.network.request;

public class RefreshTokenRequest {
    String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
