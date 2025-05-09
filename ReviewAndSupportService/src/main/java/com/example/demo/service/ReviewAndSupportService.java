package com.example.demo.service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SupportTicketDTO;
import com.example.demo.model.Review;
import com.example.demo.model.SupportTicket;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewAndSupportService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    // Review Methods
    public void submitReview(ReviewDTO reviewDTO) {
        Review reviewEntity = new Review();
        reviewEntity.setUserId(reviewDTO.getUserId());
        reviewEntity.setHotelId(reviewDTO.getHotelId());
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setComment(reviewDTO.getComment());
        reviewRepository.save(reviewEntity);
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
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
        return reviewDTOs;
    }

    // Support Ticket Methods
    public void submitSupportTicket(SupportTicketDTO ticketDTO) {
        SupportTicket ticketEntity = new SupportTicket();
        ticketEntity.setUserId(ticketDTO.getUserId());
        ticketEntity.setIssue(ticketDTO.getIssue());
        ticketEntity.setStatus(ticketDTO.getStatus());
        ticketEntity.setAssignedAgent(ticketDTO.getAssignedAgent());
        supportTicketRepository.save(ticketEntity);
    }

    public List<SupportTicketDTO> getAllSupportTickets() {
        List<SupportTicket> tickets = supportTicketRepository.findAll();
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
        return ticketDTOs;
    }
}
