package com.example.demo.service;

import com.example.demo.model.Booking;
import java.util.List;

public interface IBookingService {
    Booking createBooking(Booking booking);
    List<Booking> getBookingsByUser(Long userId);
    Booking updateBookingStatus(Long bookingId, String status);
    Booking cancelBooking(Long bookingId); // New method for cancellation
}
