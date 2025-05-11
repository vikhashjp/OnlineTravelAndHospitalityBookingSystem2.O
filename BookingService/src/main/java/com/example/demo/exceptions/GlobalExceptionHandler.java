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

@ControllerAdvice // Marks this class as a global exception handler across the entire application
public class GlobalExceptionHandler {

	/**
	 * Handles validation errors for method arguments annotated with @Valid.
	 * Captures field-specific validation messages and returns a BAD REQUEST
	 * response.
	 * 
	 * @param ex The exception thrown due to validation failure.
	 * @return A response entity containing validation error details.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", new Date());

		// Collects all validation errors and maps them to corresponding fields
		ex.getBindingResult().getAllErrors().forEach(error -> {
			body.put(((FieldError) error).getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles cases where a booking is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing booking record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleBookingNotFoundException(BookingNotFoundException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles any general exceptions that are not explicitly caught by other
	 * handlers. Returns an INTERNAL SERVER ERROR status to indicate unexpected
	 * system failures.
	 * 
	 * @param exception The unexpected exception encountered.
	 * @return A standardized error response entity.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
