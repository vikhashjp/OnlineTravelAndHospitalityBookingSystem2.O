package com.example.demo.controller;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/payments") // Defines the base path for payment-related endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to interact with this API
public class PaymentController {

	@Autowired // Injects the PaymentService to handle business logic
	private PaymentService paymentService;

	/**
	 * Processes a new payment request.
	 * 
	 * @param paymentDTO The payment details provided in the request body.
	 * @return A response entity with CREATED status if successful, or BAD REQUEST
	 *         if an error occurs.
	 */
	@PostMapping
	public ResponseEntity<String> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
		try {
			paymentService.processPayment(paymentDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Payment processed successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to process payment: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all processed payments.
	 * 
	 * @return A list of payments or an error message if retrieval fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllPayments() {
		try {
			List<PaymentDTO> payments = paymentService.getAllPayments();

			// Return the list of payments with OK status
			return ResponseEntity.ok(payments);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching payments: " + e.getMessage());
		}
	}
}
