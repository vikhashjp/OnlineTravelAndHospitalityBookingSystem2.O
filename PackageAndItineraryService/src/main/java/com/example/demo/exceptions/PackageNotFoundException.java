package com.example.demo.exceptions;

/**
 * Custom exception class for handling scenarios where a package is not found.
 * Extends {@link RuntimeException} to allow unchecked exceptions.
 */
public class PackageNotFoundException extends RuntimeException {

	/**
	 * Constructs a new {@code PackageNotFoundException} with the specified error
	 * message.
	 * 
	 * @param message Descriptive error message indicating why the package was not
	 *                found.
	 */
	public PackageNotFoundException(String message) {
		super(message);
	}
}
