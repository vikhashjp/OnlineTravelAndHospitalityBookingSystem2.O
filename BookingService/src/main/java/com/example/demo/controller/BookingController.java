package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.IBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a Spring REST controller handling HTTP requests
@RequestMapping("/api/bookings") // Defines the base path for booking-related endpoints
@CrossOrigin(origins = "*") // Enables CORS, allowing frontend applications to access this API from
							// different origins
public class BookingController {

	@Autowired // Injects the Booking Service dependency
	private IBookingService bookingService;

	/**
	 * Fetches all bookings for a specific user.
	 * 
	 * @param userId The ID of the user whose bookings are being retrieved.
	 * @return A ResponseEntity containing the bookings or an error message.
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getBookingsByUser(@PathVariable Long userId) {
		try {
			// Retrieve bookings for the given user ID
			List<Booking> bookings = bookingService.getBookingsByUser(userId);

			// If no bookings exist, return NOT FOUND response
			if (bookings.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found for this user.");
			}

			// Return the list of bookings with OK status
			return ResponseEntity.ok(bookings);
		} catch (Exception e) {
			// Handle unexpected errors and return INTERNAL SERVER ERROR response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching bookings: " + e.getMessage());
		}
	}
}
