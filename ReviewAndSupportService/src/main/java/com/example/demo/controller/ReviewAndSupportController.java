package com.example.demo.controller;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SupportTicketDTO;
import com.example.demo.service.ReviewAndSupportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/review-support") // Defines the base path for review and support endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to interact with this API
public class ReviewAndSupportController {

	@Autowired // Injects the ReviewAndSupportService to handle business logic
	private ReviewAndSupportService reviewAndSupportService;

	// Review Endpoints

	/**
	 * Submits a user review.
	 * 
	 * @param reviewDTO The review details provided by the user.
	 * @return A response entity with CREATED status if successful, or BAD REQUEST
	 *         if an error occurs.
	 */
	@PostMapping("/reviews")
	public ResponseEntity<String> submitReview(@Valid @RequestBody ReviewDTO reviewDTO) {
		try {
			reviewAndSupportService.submitReview(reviewDTO);
			return ResponseEntity.status(201).body("Review submitted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Failed to submit review: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all submitted reviews.
	 * 
	 * @return A list of reviews or an error message if retrieval fails.
	 */
	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewDTO>> getAllReviews() {
		try {
			List<ReviewDTO> reviews = reviewAndSupportService.getAllReviews();
			return ResponseEntity.ok(reviews);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	// Support Ticket Endpoints

	/**
	 * Submits a new support ticket.
	 * 
	 * @param ticketDTO The support ticket details provided by the user.
	 * @return A response entity with CREATED status if successful, or BAD REQUEST
	 *         if an error occurs.
	 */
	@PostMapping("/support-tickets")
	public ResponseEntity<String> submitSupportTicket(@Valid @RequestBody SupportTicketDTO ticketDTO) {
		try {
			reviewAndSupportService.submitSupportTicket(ticketDTO);
			return ResponseEntity.status(201).body("Support ticket submitted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Failed to submit support ticket: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all support tickets.
	 * 
	 * @return A list of support tickets or an error message if retrieval fails.
	 */
	@GetMapping("/support-tickets")
	public ResponseEntity<List<SupportTicketDTO>> getAllSupportTickets() {
		try {
			List<SupportTicketDTO> tickets = reviewAndSupportService.getAllSupportTickets();
			return ResponseEntity.ok(tickets);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
