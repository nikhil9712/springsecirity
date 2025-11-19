package com.Security.usermanagement.dto;

import java.time.LocalDate;

public class SignResponseDto {

	private String message;
	
	private LocalDate time=LocalDate.now();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "SignResponseDto [message=" + message + ", time=" + time + "]";
	}
    
}
