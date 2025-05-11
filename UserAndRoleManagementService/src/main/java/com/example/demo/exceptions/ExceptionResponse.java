package com.example.demo.exceptions;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) for handling exception responses. Stores essential
 * details such as status, message, and timestamp to provide meaningful error
 * feedback.
 */
public class ExceptionResponse {

	private int status; // Stores the HTTP status code of the error response
	private String message; // Stores the error message explaining the issue
	private LocalDateTime time; // Stores the timestamp when the exception occurred

	/**
	 * Default constructor. Initializes an empty `ExceptionResponse` instance.
	 */
	public ExceptionResponse() {
		super();
	}

	/**
	 * Parameterized constructor to initialize exception response attributes.
	 * 
	 * @param status  The HTTP status code indicating the error type.
	 * @param message The error message providing details about the issue.
	 * @param time    The timestamp when the error occurred.
	 */
	public ExceptionResponse(int status, String message, LocalDateTime time) {
		super();
		this.status = status;
		this.message = message;
		this.time = time;
	}

	/**
	 * Retrieves the error message.
	 * 
	 * @return The error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param message The error message to be assigned.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retrieves the timestamp of the exception.
	 * 
	 * @return The timestamp.
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * Sets the timestamp for when the exception occurred.
	 * 
	 * @param time The timestamp to be assigned.
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	/**
	 * Retrieves the HTTP status code.
	 * 
	 * @return The status code.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the HTTP status code for the exception response.
	 * 
	 * @param status The status code to be assigned.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
