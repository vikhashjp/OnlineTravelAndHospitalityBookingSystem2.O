package com.example.demo.exceptions;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for managing application-wide exceptions. Handles
 * validation errors, user-specific errors, and generic exceptions.
 */
@ControllerAdvice // Provides centralized exception handling across the application
public class GlobalExceptionHandler {

	/**
	 * Handles validation errors caused by invalid request parameters. Extracts
	 * field errors and returns structured error details.
	 * 
	 * @param ex The validation exception.
	 * @return A ResponseEntity containing validation error messages.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", new Date());

		// Extracting field errors from validation failure
		ex.getBindingResult().getAllErrors().forEach(error -> {
			body.put(((FieldError) error).getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles exceptions where a requested user is not found. Returns a structured
	 * error response with HTTP status 404.
	 * 
	 * @param exception The user not found exception.
	 * @return A ResponseEntity containing error details.
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles general exceptions that occur in the application. Returns a
	 * structured error response with HTTP status 500.
	 * 
	 * @param exception The generic exception.
	 * @return A ResponseEntity containing error details.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
