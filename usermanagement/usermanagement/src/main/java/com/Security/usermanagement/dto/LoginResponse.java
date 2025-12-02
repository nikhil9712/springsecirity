package com.Security.usermanagement.dto;

public class LoginResponse {

    private String message = "Successfully logged in";
    private String accessToken;
    private String refreshtoken;

    public LoginResponse() {}

    public LoginResponse(String accessToken,String refreshtoken) {
        this.accessToken = accessToken;
        this.refreshtoken = refreshtoken;
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
    

    public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	@Override
	public String toString() {
		return "LoginResponse [message=" + message + ", accessToken=" + accessToken + ", refreshtoken=" + refreshtoken
				+ "]";
	}
}
