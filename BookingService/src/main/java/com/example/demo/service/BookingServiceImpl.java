package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.Hotel;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.HotelRepository;

@Service // Marks this class as a Spring-managed service component
public class BookingServiceImpl implements IBookingService {

	private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class); // Logger for debugging

	@Autowired // Injects Booking repository to interact with the database
	private BookingRepository bookingRepository;

	@Autowired // Injects Flight repository for flight-related operations
	private FlightRepository flightRepository;

	@Autowired // Injects Hotel repository for hotel-related operations
	private HotelRepository hotelRepository;

	/**
	 * Creates a new booking and updates availability for flights or hotels.
	 * 
	 * @param booking The booking details provided by the user.
	 * @return The saved booking entity.
	 */
	@Override
	public Booking createBooking(Booking booking) {
		log.info("Creating booking: {}", booking);

		booking.setBookingDate(new Date()); // Sets the booking date to the current date
		booking.setStatus("Confirmed"); // Default booking status set to "Confirmed"

		// Handling Flight booking availability update
		if ("Flight".equalsIgnoreCase(booking.getType()) && booking.getFlightId() != null) {
			Optional<Flight> flightOpt = flightRepository.findById(booking.getFlightId());

			if (flightOpt.isPresent()) {
				Flight flight = flightOpt.get();
				if (flight.getAvailability() >= booking.getNumberOfRooms()) {
					flight.setAvailability(flight.getAvailability() - booking.getNumberOfRooms());
					flightRepository.save(flight);
					log.info("Updated flight availability: {}", flight);
				} else {
					log.error("Not enough seats available!");
					throw new RuntimeException("Not enough seats available!");
				}
			} else {
				log.error("Flight not found!");
				throw new RuntimeException("Flight not found!");
			}
		}

		// Handling Hotel booking availability update
		if ("Hotel".equalsIgnoreCase(booking.getType()) && booking.getHotelId() != null) {
			Optional<Hotel> hotelOpt = hotelRepository.findById(booking.getHotelId());

			if (hotelOpt.isPresent()) {
				Hotel hotel = hotelOpt.get();
				if (hotel.getRoomsAvailable() >= booking.getNumberOfRooms()) {
					hotel.setRoomsAvailable(hotel.getRoomsAvailable() - booking.getNumberOfRooms());
					hotelRepository.save(hotel);
					log.info("Updated hotel availability: {}", hotel);
				} else {
					log.error("Not enough rooms available!");
					throw new RuntimeException("Not enough rooms available!");
				}
			} else {
				log.error("Hotel not found!");
				throw new RuntimeException("Hotel not found!");
			}
		}

		Booking savedBooking = bookingRepository.save(booking);
		log.info("Booking successfully created: {}", savedBooking);
		return savedBooking;
	}

	/**
	 * Retrieves bookings for a specific user.
	 * 
	 * @param userId The user ID whose bookings are being fetched.
	 * @return A list of bookings associated with the user.
	 */
	@Override
	public List<Booking> getBookingsByUser(Long userId) {
		log.info("Fetching bookings for user ID: {}", userId);
		List<Booking> bookings = bookingRepository.findByUserId(userId);
		log.info("Retrieved bookings: {}", bookings);
		return bookings;
	}

	/**
	 * Updates the status of an existing booking.
	 * 
	 * @param bookingId The ID of the booking.
	 * @param status    The new status to be assigned to the booking.
	 * @return The updated booking entity.
	 */
	@Override
	public Booking updateBookingStatus(Long bookingId, String status) {
		log.info("Updating booking status for ID {} to {}", bookingId, status);
		Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.error("Booking not found with ID: {}", bookingId);
			return new BookingNotFoundException("Booking not found with ID: " + bookingId);
		});
		booking.setStatus(status);
		Booking updatedBooking = bookingRepository.save(booking);
		log.info("Booking status updated: {}", updatedBooking);
		return updatedBooking;
	}

	/**
	 * Cancels a booking and restores availability in flights or hotels.
	 * 
	 * @param bookingId The ID of the booking to be canceled.
	 * @return The canceled booking entity.
	 */
	@Override
	public Booking cancelBooking(Long bookingId) {
		log.info("Canceling booking with ID: {}", bookingId);
		Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.error("Booking not found with ID: {}", bookingId);
			return new BookingNotFoundException("Booking not found with ID: " + bookingId);
		});

		booking.setStatus("Canceled");

		log.info("Processing flight or hotel availability restoration...");

		// Restores flight availability if canceled
		if ("Flight".equalsIgnoreCase(booking.getType()) && booking.getFlightId() != null) {
			flightRepository.findById(booking.getFlightId()).ifPresent(flight -> {
				flight.setAvailability(flight.getAvailability() + booking.getNumberOfRooms());
				flightRepository.save(flight);
				log.info("Updated flight availability after cancellation: {}", flight);
			});
		}

		// Restores hotel room availability if canceled
		if ("Hotel".equalsIgnoreCase(booking.getType()) && booking.getHotelId() != null) {
			hotelRepository.findById(booking.getHotelId()).ifPresent(hotel -> {
				hotel.setRoomsAvailable(hotel.getRoomsAvailable() + booking.getNumberOfRooms());
				hotelRepository.save(hotel);
				log.info("Updated hotel availability after cancellation: {}", hotel);
			});
		}

		Booking canceledBooking = bookingRepository.save(booking);
		log.info("Booking successfully canceled: {}", canceledBooking);
		return canceledBooking;
	}
}
