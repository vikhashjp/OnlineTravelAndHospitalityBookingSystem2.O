package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice // Marks this class as a centralized exception handler for the entire
					// application
public class GlobalExceptionHandler {

	/**
	 * Handles cases where a review is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing review record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleReviewNotFoundException(ReviewNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles cases where a support ticket is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing support ticket record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(SupportTicketNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleSupportTicketNotFoundException(
			SupportTicketNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles any general exceptions that are not explicitly caught by other
	 * handlers. Returns an INTERNAL SERVER ERROR status to indicate unexpected
	 * failures.
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
