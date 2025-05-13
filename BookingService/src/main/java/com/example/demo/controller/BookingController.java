package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.dto.BookingRequest;
import com.example.demo.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling booking-related operations. Defines endpoints for
 * creating, retrieving, and canceling bookings.
 */
@RestController // Marks this class as a REST controller
@RequestMapping("/api/bookings") // Defines the base URL for booking-related endpoints
@CrossOrigin(origins = "*") // Allows cross-origin requests from any domain
public class BookingController {

	@Autowired
	private IBookingService bookingService; // Injecting the booking service dependency

	/**
	 * Creates a new booking.
	 * 
	 * @param bookingRequest The request body containing booking details.
	 * @return The newly created booking wrapped in a ResponseEntity.
	 */
	@PostMapping("/create")
	public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
		return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
	}

	/**
	 * Retrieves all bookings for a specific user.
	 * 
	 * @param userId The ID of the user whose bookings are being fetched.
	 * @return A list of bookings associated with the user.
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
	}

	/**
	 * Cancels a booking by its ID.
	 * 
	 * @param bookingId The ID of the booking to cancel.
	 * @return The updated booking with status changed to "Cancelled".
	 */
	@DeleteMapping("/cancel/{bookingId}")
	public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
		return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
	}
}
