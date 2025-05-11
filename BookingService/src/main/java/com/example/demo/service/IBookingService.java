package com.example.demo.service;

import com.example.demo.model.Booking;
import java.util.List;

/**
 * Service interface for managing booking operations. Defines the core methods
 * required for booking creation, retrieval, status updates, and cancellation.
 */
public interface IBookingService {

	/**
	 * Creates a new booking.
	 * 
	 * @param booking The booking details provided by the user.
	 * @return The saved booking entity.
	 */
	Booking createBooking(Booking booking);

	/**
	 * Retrieves bookings for a specific user.
	 * 
	 * @param userId The user ID whose bookings are being fetched.
	 * @return A list of bookings associated with the user.
	 */
	List<Booking> getBookingsByUser(Long userId);

	/**
	 * Updates the status of an existing booking.
	 * 
	 * @param bookingId The ID of the booking.
	 * @param status    The new status to be assigned to the booking.
	 * @return The updated booking entity.
	 */
	Booking updateBookingStatus(Long bookingId, String status);

	/**
	 * Cancels a booking.
	 * 
	 * @param bookingId The ID of the booking to be canceled.
	 * @return The canceled booking entity.
	 */
	Booking cancelBooking(Long bookingId); // New method for cancellation
}
