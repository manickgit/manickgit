package com.manick.rest.webservices.restfulservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime localTime;
	
	private String message;
	
	private String details;
	
	

	public ErrorDetails(LocalDateTime localTime, String message, String details) {
		super();
		this.localTime = localTime;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getLocalTime() {
		return localTime;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
}
