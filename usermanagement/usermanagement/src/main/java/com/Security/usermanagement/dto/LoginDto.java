package com.Security.usermanagement.dto;

import java.time.LocalDateTime;

public class LoginDto {
	
	private String userName;

	private String password;
	
	private LocalDateTime loggedinAt=LocalDateTime.now();
	
	private String accessToken;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getLoggedinAt() {
		return loggedinAt;
	}

	public void setLoggedinAt(LocalDateTime loggedinAt) {
		this.loggedinAt = loggedinAt;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "LoginDto [userName=" + userName + ", password=" + password + ", loggedinAt=" + loggedinAt
				+ ", accessToken=" + accessToken + "]";
	}
	
}
