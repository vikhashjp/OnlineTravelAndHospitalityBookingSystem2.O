package com.example.demo.repository;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Payment entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
