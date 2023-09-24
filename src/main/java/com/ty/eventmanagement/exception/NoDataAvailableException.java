package com.ty.eventmanagement.exception;

public class NoDataAvailableException extends RuntimeException {

	String message = "No data present";

	public NoDataAvailableException(String message) {
		this.message = message;
	}

	public NoDataAvailableException() {
	}

	public String getMessage() {
		return message;
	}

}
