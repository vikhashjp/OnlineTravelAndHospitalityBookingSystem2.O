package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.dto.BookingRequest;
import java.util.List;

/**
 * Interface for Booking Service. Defines methods for creating, retrieving, and
 * canceling bookings.
 */
public interface IBookingService {

	/**
	 * Creates a new booking based on the provided request.
	 * 
	 * @param bookingRequest The request object containing booking details.
	 * @return The newly created Booking object.
	 */
	Booking createBooking(BookingRequest bookingRequest);

	/**
	 * Retrieves a list of bookings for a specified user.
	 * 
	 * @param userId The ID of the user whose bookings are being retrieved.
	 * @return A list of Booking objects associated with the user.
	 */
	List<Booking> getBookingsByUser(Long userId);

	/**
	 * Cancels a booking by changing its status.
	 * 
	 * @param bookingId The ID of the booking to cancel.
	 * @return The updated Booking object after cancellation.
	 */
	Booking cancelBooking(Long bookingId);
}
