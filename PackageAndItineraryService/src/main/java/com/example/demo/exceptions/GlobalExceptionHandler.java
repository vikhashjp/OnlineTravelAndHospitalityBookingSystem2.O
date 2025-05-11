package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice // Marks this class as a global exception handler across the entire application
public class GlobalExceptionHandler {

	/**
	 * Handles cases where an itinerary is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing itinerary record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(ItineraryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleItineraryNotFoundException(ItineraryNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles cases where a package is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing package record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(PackageNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handlePackageNotFoundException(PackageNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
		ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
