package com.example.demo.repository;

import com.example.demo.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Itinerary entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
