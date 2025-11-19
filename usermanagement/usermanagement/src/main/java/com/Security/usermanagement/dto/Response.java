package com.Security.usermanagement.dto;

import java.time.LocalDateTime;

public class Response {

	private String message;
	private LocalDateTime dateTime = LocalDateTime.now();
	private Object object;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", dateTime=" + dateTime + ", object=" + object + "]";
	}

}
