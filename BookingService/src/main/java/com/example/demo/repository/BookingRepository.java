package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Booking entities in the database. Extends
 * {@link JpaRepository} to provide standard CRUD operations.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	/**
	 * Retrieves a list of bookings for a specific user.
	 * 
	 * @param userId The ID of the user whose bookings are being queried.
	 * @return A list of bookings associated with the provided user ID.
	 */
	List<Booking> findByUserId(Long userId);
}
