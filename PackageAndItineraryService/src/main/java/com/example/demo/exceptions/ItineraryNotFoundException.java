package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where an itinerary is not
 * found. Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class ItineraryNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code ItineraryNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the itinerary was not
	 *                found.
	 */
	public ItineraryNotFoundException(String message) {
		super(message);
	}
}
