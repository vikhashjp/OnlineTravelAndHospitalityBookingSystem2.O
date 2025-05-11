package com.example.demo.exceptions;

/**
 * Custom exception class for handling user not found scenarios. Extends
 * {@link RuntimeException} to allow unchecked exceptions.
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * Constructor that initializes the exception message.
	 * 
	 * @param message The error message indicating that the user was not found.
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
