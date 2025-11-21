package com.Security.usermanagement.dto;

public class LoginResponse {

    private String message = "Successfully logged in";
    private String accessToken;

    public LoginResponse() {}

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
        this.message = "Successfully logged in";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "LoginResponse [message=" + message + ", accessToken=" + accessToken + "]";
    }
}
