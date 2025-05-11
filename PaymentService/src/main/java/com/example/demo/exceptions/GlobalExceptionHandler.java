package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice // Marks this class as a global exception handler for handling errors across the
					// entire application
public class GlobalExceptionHandler {

	/**
	 * Handles cases where an invoice is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing invoice record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(InvoiceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleInvoiceNotFoundException(InvoiceNotFoundException exception) {
		ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles cases where a payment is not found in the system.
	 * 
	 * @param exception Custom exception indicating a missing payment record.
	 * @return A NOT FOUND response entity with error details.
	 */
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handlePaymentNotFoundException(PaymentNotFoundException exception) {
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
