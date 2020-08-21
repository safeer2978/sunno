package com.sunno.AuthModule.network.response;

public class LoginResponse {
    String accessToken;
    String refreshToken;
    String tokenType;
    Long expiresInMsec;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresInMsec() {
        return expiresInMsec;
    }

    public void setExpiresInMsec(Long expiresInMsec) {
        this.expiresInMsec = expiresInMsec;
    }
}
