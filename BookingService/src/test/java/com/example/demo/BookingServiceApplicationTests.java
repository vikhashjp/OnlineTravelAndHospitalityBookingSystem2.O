package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.IBookingService;
import com.example.demo.service.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

@ExtendWith(MockitoExtension.class) // Enables Mockito testing and allows dependency injection
@SpringBootTest // Marks this class as a Spring Boot test
class BookingServiceApplicationTests {

	@Mock // Creates a mock instance of BookingRepository
	private BookingRepository bookingRepository;

	@InjectMocks // Injects mock dependencies into BookingServiceImpl
	private BookingServiceImpl bookingService;

	/**
	 * Tests booking creation functionality.
	 */
	@Test
	void testCreateBooking() {
		// Mock Booking Data
		Booking mockBooking = new Booking();
		mockBooking.setBookingId(1L);
		mockBooking.setUserId(101L);
		mockBooking.setType("Hotel");
		mockBooking.setPaymentId(202L);
		mockBooking.setHotelId(303L);
		mockBooking.setStatus("Pending");
		mockBooking.setBookingDate(new Date());

		// Define mock behavior: Simulates repository saving functionality
		when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

		// Call Service Method
		Booking result = bookingService.createBooking(mockBooking);

		// Assertions: Verify returned booking matches expected values
		assertNotNull(result);
		assertEquals(101L, result.getUserId());
		assertEquals("Hotel", result.getType());
		assertEquals("Pending", result.getStatus());

		// Verify interaction: Ensure save method is called exactly once
		verify(bookingRepository, times(1)).save(any(Booking.class));
	}

	/**
	 * Tests fetching bookings for a specific user.
	 */
	@Test
	void testGetBookingsByUser() {
		// Mock Booking Data
		Booking booking1 = new Booking();
		booking1.setBookingId(1L);
		booking1.setUserId(101L);
		booking1.setType("Hotel");

		Booking booking2 = new Booking();
		booking2.setBookingId(2L);
		booking2.setUserId(101L);
		booking2.setType("Flight");

		// Define mock behavior: Simulates database query
		when(bookingRepository.findByUserId(101L)).thenReturn(Arrays.asList(booking1, booking2));

		// Call Service Method
		List<Booking> bookings = bookingService.getBookingsByUser(101L);

		// Assertions: Verify correct number of bookings returned
		assertNotNull(bookings);
		assertEquals(2, bookings.size());

		// Verify interaction: Ensure repository method is called once
		verify(bookingRepository, times(1)).findByUserId(101L);
	}

	/**
	 * Tests successful booking status update.
	 */
	@Test
	void testUpdateBookingStatus_Success() {
		// Mock Booking Data
		Booking mockBooking = new Booking();
		mockBooking.setBookingId(1L);
		mockBooking.setStatus("Pending");

		// Define mock behavior: Simulates finding and updating booking status
		when(bookingRepository.findById(1L)).thenReturn(Optional.of(mockBooking));
		when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

		// Call Service Method
		Booking updatedBooking = bookingService.updateBookingStatus(1L, "Confirmed");

		// Assertions: Verify booking status change
		assertNotNull(updatedBooking);
		assertEquals("Confirmed", updatedBooking.getStatus());

		// Verify interactions: Ensure repository find/save methods are called
		verify(bookingRepository, times(1)).findById(1L);
		verify(bookingRepository, times(1)).save(any(Booking.class));
	}

	/**
	 * Tests booking status update failure due to non-existent booking.
	 */
	@Test
	void testUpdateBookingStatus_NotFound() {
		// Define mock behavior for non-existent booking: Simulates empty result
		when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

		// Expect exception: Verify BookingNotFoundException is thrown
		assertThrows(BookingNotFoundException.class, () -> bookingService.updateBookingStatus(1L, "Confirmed"));

		// Verify interaction: Ensure repository find method is called once and save
		// method is never called
		verify(bookingRepository, times(1)).findById(1L);
		verify(bookingRepository, never()).save(any(Booking.class));
	}
}
