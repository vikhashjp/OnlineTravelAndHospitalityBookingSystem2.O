package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where a support ticket is not
 * found. Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class SupportTicketNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code SupportTicketNotFoundException} with the specified
	 * error message.
	 * 
	 * @param message Descriptive error message indicating why the support ticket
	 *                was not found.
	 */
	public SupportTicketNotFoundException(String message) {
		super(message);
	}
}
