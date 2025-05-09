package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public ResponseEntity<Object> createHotel(@Valid @RequestBody Hotel hotel) {
        try {
            Hotel savedHotel = hotelRepository.save(hotel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create hotel: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllHotels() {
        try {
            List<Hotel> hotels = hotelRepository.findAll();
            if (hotels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hotels found");
            }
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching hotels: " + e.getMessage());
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Object> getHotelById(@PathVariable Long hotelId) {
        try {
            Optional<Hotel> hotel = hotelRepository.findById(hotelId);
            if (hotel.isPresent()) {
                return ResponseEntity.ok(hotel.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching hotel: " + e.getMessage());
        }
    }
}
