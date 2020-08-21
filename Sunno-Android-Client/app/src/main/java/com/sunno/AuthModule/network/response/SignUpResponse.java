package com.sunno.AuthModule.network.response;

public class SignUpResponse {
    String message;
    Boolean status;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
