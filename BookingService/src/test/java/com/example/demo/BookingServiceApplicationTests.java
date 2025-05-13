package com.example.demo;

import com.example.demo.dto.BookingRequest;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceApplicationTests {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void testCreateBooking() {
        BookingRequest bookingRequest = new BookingRequest();
        Booking mockBooking = new Booking();
        mockBooking.setUserId(101L);
        mockBooking.setPackageId(10L);
        mockBooking.setType("Hotel");
        mockBooking.setHotelId(303L);
        mockBooking.setNumberOfRooms(2);
        mockBooking.setStatus("Confirmed");

        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        Booking result = bookingService.createBooking(bookingRequest);

        assertNotNull(result);
        assertEquals("Confirmed", result.getStatus());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testGetBookingsByUser() {
        Booking booking1 = new Booking();
        booking1.setBookingId(1L);
        booking1.setUserId(101L);
        Booking booking2 = new Booking();
        booking2.setBookingId(2L);
        booking2.setUserId(101L);

        when(bookingRepository.findByUserId(101L)).thenReturn(Arrays.asList(booking1, booking2));

        List<Booking> bookings = bookingService.getBookingsByUser(101L);

        assertEquals(2, bookings.size());
        verify(bookingRepository, times(1)).findByUserId(101L);
    }

    @Test
    void testCancelBooking_Success() {
        Booking mockBooking = new Booking();
        mockBooking.setBookingId(1L);
        mockBooking.setStatus("Confirmed");

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(mockBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        Booking result = bookingService.cancelBooking(1L);

        assertEquals("Cancelled", result.getStatus());
        verify(bookingRepository, times(1)).findById(1L);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testCancelBooking_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingService.cancelBooking(1L));

        verify(bookingRepository, times(1)).findById(1L);
    }
}
