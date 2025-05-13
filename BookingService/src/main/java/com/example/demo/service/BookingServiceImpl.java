package com.example.demo.service;

import com.example.demo.dto.BookingRequest;
import com.example.demo.dto.PackageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.feignclients.PackageClient;
import com.example.demo.feignclients.UserClient;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of IBookingService that manages booking operations. Handles
 * creation, validation, retrieval, and cancellation of bookings.
 */
@Service
public class BookingServiceImpl implements IBookingService {

	// Logger instance for debugging and tracking
	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	// Injecting BookingRepository for database operations
	@Autowired
	private BookingRepository bookingRepository;

	// Injecting UserClient to fetch user-related details from another microservice
	@Autowired
	private UserClient userClient;

	// Injecting PackageClient to fetch package details from another microservice
	@Autowired
	private PackageClient packageClient;

	/**
	 * Creates a new booking. Validates booking details before saving it in the
	 * database.
	 * 
	 * @param bookingRequest The request object containing booking details.
	 * @return The saved Booking object.
	 */
	@Override
	public Booking createBooking(BookingRequest bookingRequest) {
		logger.info("Attempting to create booking for user ID: {}", bookingRequest.getUserId());

		// Validate booking parameters before proceeding
		validateBooking(bookingRequest);

		// Creating a Booking object and setting its properties
		Booking booking = new Booking();
		booking.setUserId(bookingRequest.getUserId());
		booking.setPackageId(bookingRequest.getPackageId());
		booking.setType(bookingRequest.getType());
		booking.setHotelId(bookingRequest.getHotelId());
		booking.setFlightId(bookingRequest.getFlightId());
		booking.setNumberOfRooms(bookingRequest.getNumberOfRooms());
		booking.setNumberOfSeats(bookingRequest.getNumberOfSeats());
		booking.setStatus("Confirmed");

		// Saving the booking to the database
		Booking savedBooking = bookingRepository.save(booking);
		logger.info("Booking successfully created with ID: {}", savedBooking.getBookingId());

		return savedBooking;
	}

	/**
	 * Validates booking details based on booking type. Ensures required fields are
	 * provided before booking creation.
	 * 
	 * @param bookingRequest The request object containing booking details.
	 * @throws IllegalArgumentException if mandatory fields are missing.
	 */
	private void validateBooking(BookingRequest bookingRequest) {
		logger.debug("Validating booking request: {}", bookingRequest);

		// If the booking type is "Hotel", ensure number of rooms is provided
		if ("Hotel".equalsIgnoreCase(bookingRequest.getType()) && bookingRequest.getNumberOfRooms() == null) {
			logger.error("Validation failed: Number of rooms is required for hotel booking.");
			throw new IllegalArgumentException("Number of rooms is required for hotel booking.");
		}

		// If the booking type is "Flight", ensure number of seats is provided
		if ("Flight".equalsIgnoreCase(bookingRequest.getType()) && bookingRequest.getNumberOfSeats() == null) {
			logger.error("Validation failed: Number of seats is required for flight booking.");
			throw new IllegalArgumentException("Number of seats is required for flight booking.");
		}

		logger.debug("Validation successful for booking request.");
	}

	/**
	 * Retrieves a list of bookings for a given user ID.
	 * 
	 * @param userId The ID of the user whose bookings are being retrieved.
	 * @return List of Booking objects belonging to the user.
	 */
	@Override
	public List<Booking> getBookingsByUser(Long userId) {
		logger.info("Fetching bookings for user ID: {}", userId);

		List<Booking> bookings = bookingRepository.findByUserId(userId);
		logger.info("Found {} bookings for user ID: {}", bookings.size(), userId);

		return bookings;
	}

	/**
	 * Cancels a booking by changing its status to "Cancelled". Throws an exception
	 * if the booking ID does not exist.
	 * 
	 * @param bookingId The ID of the booking to cancel.
	 * @return The updated Booking object after cancellation.
	 * @throws BookingNotFoundException if the booking is not found.
	 */
	@Override
	public Booking cancelBooking(Long bookingId) {
		logger.info("Attempting to cancel booking with ID: {}", bookingId);

		// Retrieve booking from the database; throw exception if not found
		Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> {
			logger.error("Booking with ID {} not found.", bookingId);
			return new BookingNotFoundException("Booking not found");
		});

		// Update booking status to "Cancelled"
		booking.setStatus("Cancelled");

		// Save the updated booking
		Booking cancelledBooking = bookingRepository.save(booking);
		logger.info("Booking with ID {} successfully cancelled.", bookingId);

		return cancelledBooking;
	}
}
