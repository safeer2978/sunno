package com.sunno.AuthModule.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */

@Entity(tableName = "login_database")
public class User {
    @NonNull
    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresInMsec() {
        return expiresInMsec;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiresInMsec(Long expiresInMsec) {
        this.expiresInMsec = expiresInMsec;
    }

    public String displayName;

    @NonNull
    @PrimaryKey
    public String email;

    public String accessToken;
    public String refreshToken;
    public String tokenType;
    public  Long expiresInMsec;


    public User(@NonNull String email, String accessToken, String refreshToken, String tokenType, Long expiresInMsec) {
        this.displayName = email;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresInMsec = expiresInMsec;
    }


    public String getDisplayName() {
        return displayName;
    }
}