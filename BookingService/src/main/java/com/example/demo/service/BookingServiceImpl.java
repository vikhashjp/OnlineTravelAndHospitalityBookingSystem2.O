package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.Hotel;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.HotelRepository;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Booking createBooking(Booking booking) {
        booking.setBookingDate(new Date());
        booking.setStatus("Confirmed");

        if ("Flight".equalsIgnoreCase(booking.getType()) && booking.getFlightId() != null) {
            Optional<Flight> flightOpt = flightRepository.findById(booking.getFlightId());

            if (flightOpt.isPresent()) {
                Flight flight = flightOpt.get();
                if (flight.getAvailability() >= booking.getNumberOfRooms()) {
                    flight.setAvailability(flight.getAvailability() - booking.getNumberOfRooms());
                    flightRepository.save(flight);
                } else {
                    throw new RuntimeException("Not enough seats available!");
                }
            } else {
                throw new RuntimeException("Flight not found!");
            }
        }

        if ("Hotel".equalsIgnoreCase(booking.getType()) && booking.getHotelId() != null) {
            Optional<Hotel> hotelOpt = hotelRepository.findById(booking.getHotelId());

            if (hotelOpt.isPresent()) {
                Hotel hotel = hotelOpt.get();
                if (hotel.getRoomsAvailable() >= booking.getNumberOfRooms()) {
                    hotel.setRoomsAvailable(hotel.getRoomsAvailable() - booking.getNumberOfRooms());
                    hotelRepository.save(hotel);
                } else {
                    throw new RuntimeException("Not enough rooms available!");
                }
            } else {
                throw new RuntimeException("Hotel not found!");
            }
        }

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        bookings.forEach(booking -> {
            if ("Hotel".equalsIgnoreCase(booking.getType()) && booking.getHotelId() != null) {
                Optional<Hotel> hotelOpt = hotelRepository.findById(booking.getHotelId());
                hotelOpt.ifPresent(booking::setHotel);
            } else if ("Flight".equalsIgnoreCase(booking.getType()) && booking.getFlightId() != null) {
                Optional<Flight> flightOpt = flightRepository.findById(booking.getFlightId());
                flightOpt.ifPresent(booking::setFlight);
            }
        });

        return bookings;
    }

    @Override
    public Booking updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        booking.setStatus("Canceled");

        if ("Flight".equalsIgnoreCase(booking.getType()) && booking.getFlightId() != null) {
            Optional<Flight> flightOpt = flightRepository.findById(booking.getFlightId());
            flightOpt.ifPresent(flight -> {
                flight.setAvailability(flight.getAvailability() + booking.getNumberOfRooms());
                flightRepository.save(flight);
            });
        }

        if ("Hotel".equalsIgnoreCase(booking.getType()) && booking.getHotelId() != null) {
            Optional<Hotel> hotelOpt = hotelRepository.findById(booking.getHotelId());
            hotelOpt.ifPresent(hotel -> {
                hotel.setRoomsAvailable(hotel.getRoomsAvailable() + booking.getNumberOfRooms());
                hotelRepository.save(hotel);
            });
        }

        return bookingRepository.save(booking);
    }
}
