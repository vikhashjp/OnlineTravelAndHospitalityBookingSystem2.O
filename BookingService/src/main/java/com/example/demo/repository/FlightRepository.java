package com.example.demo.repository;

import com.example.demo.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Flight entities in the database. Extends
 * {@link JpaRepository} to provide standard CRUD operations.
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

	/**
	 * Retrieves a flight by its unique ID.
	 * 
	 * @param flightId The ID of the flight to be fetched.
	 * @return An {@link Optional} containing the flight details if found.
	 */
	Optional<Flight> findById(Long flightId);
}
