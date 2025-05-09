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

@SpringBootTest
class ReviewAndSupportServiceApplicationTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private SupportTicketRepository supportTicketRepository;

    @InjectMocks
    private ReviewAndSupportService reviewAndSupportService;

    @Test
    void testSubmitReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUserId(101L);
        reviewDTO.setHotelId(202L);
        reviewDTO.setRating(5);
        reviewDTO.setComment("Amazing experience!");

        Review reviewEntity = new Review();
        reviewEntity.setId(1L);
        reviewEntity.setUserId(reviewDTO.getUserId());
        reviewEntity.setHotelId(reviewDTO.getHotelId());
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setComment(reviewDTO.getComment());

        when(reviewRepository.save(any(Review.class))).thenReturn(reviewEntity);

        reviewAndSupportService.submitReview(reviewDTO);

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

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

        when(reviewRepository.findAll()).thenReturn(reviews);

        List<ReviewDTO> reviewDTOs = reviewAndSupportService.getAllReviews();

        assertNotNull(reviewDTOs);
        assertEquals(2, reviewDTOs.size());
        assertEquals("Great stay!", reviewDTOs.get(0).getComment());

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testSubmitSupportTicket() {
        SupportTicketDTO ticketDTO = new SupportTicketDTO();
        ticketDTO.setUserId(101L);
        ticketDTO.setIssue("Payment not processed");
        ticketDTO.setStatus("Open");
        ticketDTO.setAssignedAgent("Agent1");

        SupportTicket ticketEntity = new SupportTicket();
        ticketEntity.setId(1L);
        ticketEntity.setUserId(ticketDTO.getUserId());
        ticketEntity.setIssue(ticketDTO.getIssue());
        ticketEntity.setStatus(ticketDTO.getStatus());
        ticketEntity.setAssignedAgent(ticketDTO.getAssignedAgent());

        when(supportTicketRepository.save(any(SupportTicket.class))).thenReturn(ticketEntity);

        reviewAndSupportService.submitSupportTicket(ticketDTO);

        verify(supportTicketRepository, times(1)).save(any(SupportTicket.class));
    }

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

        when(supportTicketRepository.findAll()).thenReturn(tickets);

        List<SupportTicketDTO> ticketDTOs = reviewAndSupportService.getAllSupportTickets();

        assertNotNull(ticketDTOs);
        assertEquals(2, ticketDTOs.size());
        assertEquals("Payment not processed", ticketDTOs.get(0).getIssue());

        verify(supportTicketRepository, times(1)).findAll();
    }
}
