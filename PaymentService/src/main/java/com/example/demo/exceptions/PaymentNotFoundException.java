package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where a payment is not found.
 * Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class PaymentNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code PaymentNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the payment was not
	 *                found.
	 */
	public PaymentNotFoundException(String message) {
		super(message);
	}
}
