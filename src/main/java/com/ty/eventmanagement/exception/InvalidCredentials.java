package com.ty.eventmanagement.exception;

public class InvalidCredentials extends RuntimeException {

	String message = "Invalid credentials";

	public InvalidCredentials(String message) {
		this.message = message;
	}

	public InvalidCredentials() {
	}

	public String getMessage() {
		return message;
	}

}
