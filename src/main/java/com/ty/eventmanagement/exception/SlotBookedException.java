package com.ty.eventmanagement.exception;

public class SlotBookedException extends RuntimeException {

	String message = "Slot already booked";

	public SlotBookedException(String message) {
		this.message = message;
	}

	public SlotBookedException() {
	}

	public String getMessage() {
		return message;
	}
}
