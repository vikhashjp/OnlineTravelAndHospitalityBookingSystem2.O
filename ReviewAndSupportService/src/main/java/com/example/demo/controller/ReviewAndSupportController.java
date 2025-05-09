package com.example.demo.controller;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SupportTicketDTO;
import com.example.demo.service.ReviewAndSupportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review-support")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class ReviewAndSupportController {

    @Autowired
    private ReviewAndSupportService reviewAndSupportService;

    // Review Endpoints
    @PostMapping("/reviews")
    public ResponseEntity<String> submitReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        try {
            reviewAndSupportService.submitReview(reviewDTO);
            return ResponseEntity.status(201).body("Review submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to submit review: " + e.getMessage());
        }
    }

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
    @PostMapping("/support-tickets")
    public ResponseEntity<String> submitSupportTicket(@Valid @RequestBody SupportTicketDTO ticketDTO) {
        try {
            reviewAndSupportService.submitSupportTicket(ticketDTO);
            return ResponseEntity.status(201).body("Support ticket submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to submit support ticket: " + e.getMessage());
        }
    }

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
