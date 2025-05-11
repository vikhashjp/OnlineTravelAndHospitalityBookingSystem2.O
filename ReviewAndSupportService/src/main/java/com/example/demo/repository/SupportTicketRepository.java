package com.example.demo.repository;

import com.example.demo.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing SupportTicket entities in the database.
 * Extends {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
}
