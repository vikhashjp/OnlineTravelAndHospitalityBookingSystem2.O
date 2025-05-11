package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Review entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
