package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Hotel entities in the database. Extends
 * {@link JpaRepository} to provide standard CRUD operations.
 */
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	/**
	 * Retrieves a hotel by its unique ID.
	 * 
	 * @param hotelId The ID of the hotel to be fetched.
	 * @return An {@link Optional} containing the hotel details if found.
	 */
	Optional<Hotel> findById(Long hotelId);
}
