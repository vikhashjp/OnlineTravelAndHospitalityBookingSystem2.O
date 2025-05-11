package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where a review is not found.
 * Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class ReviewNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code ReviewNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the review was not
	 *                found.
	 */
	public ReviewNotFoundException(String message) {
		super(message);
	}
}
