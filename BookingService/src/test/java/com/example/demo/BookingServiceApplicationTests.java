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

@ExtendWith(MockitoExtension.class) // Enables Mockito testing
@SpringBootTest
class BookingServiceApplicationTests {

    @Mock
    private BookingRepository bookingRepository; // Mock Repository

    @InjectMocks
    private BookingServiceImpl bookingService; // Inject Implementation of IBookingService

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

        // Define mock behavior
        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        // Call Service Method
        Booking result = bookingService.createBooking(mockBooking);

        // Assertions
        assertNotNull(result);
        assertEquals(101L, result.getUserId());
        assertEquals("Hotel", result.getType());
        assertEquals("Pending", result.getStatus());

        // Verify interaction
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

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

        // Define mock behavior
        when(bookingRepository.findByUserId(101L)).thenReturn(Arrays.asList(booking1, booking2));

        // Call Service Method
        List<Booking> bookings = bookingService.getBookingsByUser(101L);

        // Assertions
        assertNotNull(bookings);
        assertEquals(2, bookings.size());

        // Verify interaction
        verify(bookingRepository, times(1)).findByUserId(101L);
    }

    @Test
    void testUpdateBookingStatus_Success() {
        // Mock Booking Data
        Booking mockBooking = new Booking();
        mockBooking.setBookingId(1L);
        mockBooking.setStatus("Pending");

        // Define mock behavior
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(mockBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        // Call Service Method
        Booking updatedBooking = bookingService.updateBookingStatus(1L, "Confirmed");

        // Assertions
        assertNotNull(updatedBooking);
        assertEquals("Confirmed", updatedBooking.getStatus());

        // Verify interactions
        verify(bookingRepository, times(1)).findById(1L);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testUpdateBookingStatus_NotFound() {
        // Define mock behavior for non-existent booking
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        // Expect exception
        assertThrows(BookingNotFoundException.class, () -> bookingService.updateBookingStatus(1L, "Confirmed"));

        // Verify interaction
        verify(bookingRepository, times(1)).findById(1L);
        verify(bookingRepository, never()).save(any(Booking.class));
    }
}
