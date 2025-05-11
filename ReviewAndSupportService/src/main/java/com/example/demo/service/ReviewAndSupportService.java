package com.example.demo.service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SupportTicketDTO;
import com.example.demo.exceptions.ReviewNotFoundException;
import com.example.demo.exceptions.SupportTicketNotFoundException;
import com.example.demo.model.Review;
import com.example.demo.model.SupportTicket;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.SupportTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Marks this class as a Spring-managed service component
public class ReviewAndSupportService {

	private static final Logger log = LoggerFactory.getLogger(ReviewAndSupportService.class); // Logger for debugging

	@Autowired // Injects the Review repository to interact with the database
	private ReviewRepository reviewRepository;

	@Autowired // Injects the Support Ticket repository to manage support requests
	private SupportTicketRepository supportTicketRepository;

	// Review Methods

	/**
	 * Submits a user review and stores it in the database.
	 * 
	 * @param reviewDTO The review details provided by the user.
	 */
	public void submitReview(ReviewDTO reviewDTO) {
		log.info("Submitting review for User ID: {}", reviewDTO.getUserId());

		// Create new Review entity
		Review reviewEntity = new Review();
		reviewEntity.setUserId(reviewDTO.getUserId());
		reviewEntity.setHotelId(reviewDTO.getHotelId());
		reviewEntity.setRating(reviewDTO.getRating());
		reviewEntity.setComment(reviewDTO.getComment());

		// Save review in the repository
		reviewRepository.save(reviewEntity);
		log.info("Review submitted successfully: {}", reviewEntity);
	}

	/**
	 * Retrieves all submitted reviews from the database. Converts entity objects
	 * into DTOs for data transfer.
	 * 
	 * @return A list of review DTOs.
	 * @throws ReviewNotFoundException if no reviews exist.
	 */
	public List<ReviewDTO> getAllReviews() {
		log.info("Fetching all reviews...");
		List<Review> reviews = reviewRepository.findAll();

		// Validate review existence
		if (reviews.isEmpty()) {
			log.error("No reviews found.");
			throw new ReviewNotFoundException("No reviews found.");
		}

		// Convert entity list to DTO list
		List<ReviewDTO> reviewDTOs = new ArrayList<>();
		for (Review review : reviews) {
			ReviewDTO dto = new ReviewDTO();
			dto.setId(review.getId());
			dto.setUserId(review.getUserId());
			dto.setHotelId(review.getHotelId());
			dto.setRating(review.getRating());
			dto.setComment(review.getComment());
			reviewDTOs.add(dto);
		}

		log.info("Total reviews fetched: {}", reviewDTOs.size());
		return reviewDTOs;
	}

	// Support Ticket Methods

	/**
	 * Submits a new support ticket and stores it in the database.
	 * 
	 * @param ticketDTO The support ticket details provided by the user.
	 */
	public void submitSupportTicket(SupportTicketDTO ticketDTO) {
		log.info("Submitting support ticket for User ID: {}", ticketDTO.getUserId());

		// Create new SupportTicket entity
		SupportTicket ticketEntity = new SupportTicket();
		ticketEntity.setUserId(ticketDTO.getUserId());
		ticketEntity.setIssue(ticketDTO.getIssue());
		ticketEntity.setStatus(ticketDTO.getStatus());
		ticketEntity.setAssignedAgent(ticketDTO.getAssignedAgent());

		// Save support ticket in the repository
		supportTicketRepository.save(ticketEntity);
		log.info("Support ticket submitted successfully: {}", ticketEntity);
	}

	/**
	 * Retrieves all submitted support tickets from the database. Converts entity
	 * objects into DTOs for data transfer.
	 * 
	 * @return A list of support ticket DTOs.
	 * @throws SupportTicketNotFoundException if no support tickets exist.
	 */
	public List<SupportTicketDTO> getAllSupportTickets() {
		log.info("Fetching all support tickets...");
		List<SupportTicket> tickets = supportTicketRepository.findAll();

		// Validate ticket existence
		if (tickets.isEmpty()) {
			log.error("No support tickets found.");
			throw new SupportTicketNotFoundException("No support tickets found.");
		}

		// Convert entity list to DTO list
		List<SupportTicketDTO> ticketDTOs = new ArrayList<>();
		for (SupportTicket ticket : tickets) {
			SupportTicketDTO dto = new SupportTicketDTO();
			dto.setId(ticket.getId());
			dto.setUserId(ticket.getUserId());
			dto.setIssue(ticket.getIssue());
			dto.setStatus(ticket.getStatus());
			dto.setAssignedAgent(ticket.getAssignedAgent());
			ticketDTOs.add(dto);
		}

		log.info("Total support tickets fetched: {}", ticketDTOs.size());
		return ticketDTOs;
	}
}
