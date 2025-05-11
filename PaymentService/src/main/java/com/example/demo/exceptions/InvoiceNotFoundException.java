package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where an invoice is not found.
 * Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class InvoiceNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code InvoiceNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the invoice was not
	 *                found.
	 */
	public InvoiceNotFoundException(String message) {
		super(message);
	}
}
