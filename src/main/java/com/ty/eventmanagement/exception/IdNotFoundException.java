package com.ty.eventmanagement.exception;

public class IdNotFoundException extends RuntimeException {

	String message = "Id not found";

	public IdNotFoundException(String message) {
		this.message = message;
	}

	public IdNotFoundException() {
	}

	public String getMessage() {
		return message;
	}

}
