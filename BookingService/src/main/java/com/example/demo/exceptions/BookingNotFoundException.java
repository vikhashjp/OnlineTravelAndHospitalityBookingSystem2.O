package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where a booking is not found.
 * Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class BookingNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code BookingNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the booking was not
	 *                found.
	 */
	public BookingNotFoundException(String message) {
		super(message);
	}
}
