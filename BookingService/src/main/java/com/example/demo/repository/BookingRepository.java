package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Booking entity. Extends JpaRepository to provide
 * CRUD operations.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	/**
	 * Retrieves a list of bookings for a specific user.
	 * 
	 * @param userId The ID of the user.
	 * @return List of Booking objects associated with the user.
	 */
	List<Booking> findByUserId(Long userId);
}
