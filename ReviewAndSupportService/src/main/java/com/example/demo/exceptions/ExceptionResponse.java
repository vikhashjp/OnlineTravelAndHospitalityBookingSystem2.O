package com.example.demo.exceptions;

import java.time.LocalDateTime;

/**
 * Represents a standardized error response structure for exceptions. This class
 * encapsulates HTTP status, error message, and timestamp.
 */
public class ExceptionResponse {

	private int status; // HTTP status code (e.g., 404, 500)
	private String message; // Detailed error message
	private LocalDateTime time; // Timestamp when the exception occurred

	/**
	 * Default constructor for ExceptionResponse. Used when manually creating an
	 * instance without predefined values.
	 */
	public ExceptionResponse() {
	}

	/**
	 * Parameterized constructor to initialize ExceptionResponse fields.
	 * 
	 * @param status  The HTTP status code associated with the exception.
	 * @param message A descriptive error message explaining the issue.
	 * @param time    The timestamp when the exception occurred.
	 */
	public ExceptionResponse(int status, String message, LocalDateTime time) {
		this.status = status;
		this.message = message;
		this.time = time;
	}

	/**
	 * Retrieves the HTTP status code related to the exception.
	 * 
	 * @return The HTTP status code.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the HTTP status code for the exception response.
	 * 
	 * @param status The HTTP status code.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Retrieves the error message associated with the exception.
	 * 
	 * @return The error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message for the exception response.
	 * 
	 * @param message The error message to be stored.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retrieves the timestamp when the exception occurred.
	 * 
	 * @return The timestamp.
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * Sets the timestamp for the exception response.
	 * 
	 * @param time The timestamp to be recorded.
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
