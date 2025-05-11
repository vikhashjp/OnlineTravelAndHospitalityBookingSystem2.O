package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SupportTicketDTO;
import com.example.demo.model.Review;
import com.example.demo.model.SupportTicket;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.SupportTicketRepository;
import com.example.demo.service.ReviewAndSupportService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Marks this class as a Spring Boot test configuration
class ReviewAndSupportServiceApplicationTests {

	@Mock // Creates a mock instance of ReviewRepository for testing
	private ReviewRepository reviewRepository;

	@Mock // Creates a mock instance of SupportTicketRepository for testing
	private SupportTicketRepository supportTicketRepository;

	@InjectMocks // Injects the mocked repositories into ReviewAndSupportService
	private ReviewAndSupportService reviewAndSupportService;

	/**
	 * Tests the review submission functionality. Ensures that a review is correctly
	 * saved in the repository.
	 */
	@Test
	void testSubmitReview() {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setUserId(101L);
		reviewDTO.setHotelId(202L);
		reviewDTO.setRating(5);
		reviewDTO.setComment("Amazing experience!");

		// Mocking review entity behavior
		Review reviewEntity = new Review();
		reviewEntity.setId(1L);
		reviewEntity.setUserId(reviewDTO.getUserId());
		reviewEntity.setHotelId(reviewDTO.getHotelId());
		reviewEntity.setRating(reviewDTO.getRating());
		reviewEntity.setComment(reviewDTO.getComment());

		when(reviewRepository.save(any(Review.class))).thenReturn(reviewEntity);

		// Execute service method
		reviewAndSupportService.submitReview(reviewDTO);

		// Verify that the repository method was called once
		verify(reviewRepository, times(1)).save(any(Review.class));
	}

	/**
	 * Tests the retrieval of all reviews from the database. Ensures correct data
	 * conversion and validation.
	 */
	@Test
	void testGetAllReviews() {
		Review review1 = new Review();
		review1.setId(1L);
		review1.setUserId(101L);
		review1.setHotelId(202L);
		review1.setRating(5);
		review1.setComment("Great stay!");

		Review review2 = new Review();
		review2.setId(2L);
		review2.setUserId(102L);
		review2.setHotelId(203L);
		review2.setRating(4);
		review2.setComment("Nice hotel!");

		List<Review> reviews = Arrays.asList(review1, review2);

		// Mock repository behavior
		when(reviewRepository.findAll()).thenReturn(reviews);

		// Execute service method
		List<ReviewDTO> reviewDTOs = reviewAndSupportService.getAllReviews();

		// Assertions to verify correct retrieval
		assertNotNull(reviewDTOs);
		assertEquals(2, reviewDTOs.size());
		assertEquals("Great stay!", reviewDTOs.get(0).getComment());

		// Verify repository interaction
		verify(reviewRepository, times(1)).findAll();
	}

	/**
	 * Tests the support ticket submission functionality. Ensures that a support
	 * ticket is correctly saved in the repository.
	 */
	@Test
	void testSubmitSupportTicket() {
		SupportTicketDTO ticketDTO = new SupportTicketDTO();
		ticketDTO.setUserId(101L);
		ticketDTO.setIssue("Payment not processed");
		ticketDTO.setStatus("Open");
		ticketDTO.setAssignedAgent("Agent1");

		// Mocking support ticket entity behavior
		SupportTicket ticketEntity = new SupportTicket();
		ticketEntity.setId(1L);
		ticketEntity.setUserId(ticketDTO.getUserId());
		ticketEntity.setIssue(ticketDTO.getIssue());
		ticketEntity.setStatus(ticketDTO.getStatus());
		ticketEntity.setAssignedAgent(ticketDTO.getAssignedAgent());

		when(supportTicketRepository.save(any(SupportTicket.class))).thenReturn(ticketEntity);

		// Execute service method
		reviewAndSupportService.submitSupportTicket(ticketDTO);

		// Verify that the repository method was called once
		verify(supportTicketRepository, times(1)).save(any(SupportTicket.class));
	}

	/**
	 * Tests the retrieval of all support tickets from the database. Ensures correct
	 * data conversion and validation.
	 */
	@Test
	void testGetAllSupportTickets() {
		SupportTicket ticket1 = new SupportTicket();
		ticket1.setId(1L);
		ticket1.setUserId(101L);
		ticket1.setIssue("Payment not processed");
		ticket1.setStatus("Open");
		ticket1.setAssignedAgent("Agent1");

		SupportTicket ticket2 = new SupportTicket();
		ticket2.setId(2L);
		ticket2.setUserId(102L);
		ticket2.setIssue("Refund delayed");
		ticket2.setStatus("Closed");
		ticket2.setAssignedAgent("Agent2");

		List<SupportTicket> tickets = Arrays.asList(ticket1, ticket2);

		// Mock repository behavior
		when(supportTicketRepository.findAll()).thenReturn(tickets);

		// Execute service method
		List<SupportTicketDTO> ticketDTOs = reviewAndSupportService.getAllSupportTickets();

		// Assertions to verify correct retrieval
		assertNotNull(ticketDTOs);
		assertEquals(2, ticketDTOs.size());
		assertEquals("Payment not processed", ticketDTOs.get(0).getIssue());

		// Verify repository interaction
		verify(supportTicketRepository, times(1)).findAll();
	}
}
