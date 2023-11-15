package com.common.api.util;

public class CustomException extends Exception {
 
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CustomException(String errorMessage) {
		this.message = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
	    return message;
	}
	
}
