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

@RestController // Marks this class as a Spring REST controller, enabling request handling
@RequestMapping("/api/hotels") // Defines the base URL path for hotel-related endpoints
@CrossOrigin(origins = "*") // Enables CORS, allowing frontend applications to interact with this API
public class HotelController {

	@Autowired // Injects the HotelRepository to interact with the database
	private HotelRepository hotelRepository;

	/**
	 * Creates a new hotel entry in the database.
	 * 
	 * @param hotel The hotel object provided in the request body.
	 * @return The saved hotel object with HTTP status CREATED or an error message
	 *         if creation fails.
	 */
	@PostMapping
	public ResponseEntity<Object> createHotel(@Valid @RequestBody Hotel hotel) {
		try {
			// Saves hotel information to the database
			Hotel savedHotel = hotelRepository.save(hotel);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
		} catch (Exception e) {
			// Handles errors and returns BAD REQUEST status
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create hotel: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all hotels from the database.
	 * 
	 * @return A list of hotels or an error message if the database retrieval fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllHotels() {
		try {
			List<Hotel> hotels = hotelRepository.findAll();

			// If no hotels exist, return NO CONTENT status
			if (hotels.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hotels found");
			}

			// Return the list of hotels with OK status
			return ResponseEntity.ok(hotels);
		} catch (Exception e) {
			// Handles unexpected errors and returns INTERNAL SERVER ERROR status
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching hotels: " + e.getMessage());
		}
	}

	/**
	 * Retrieves a hotel by its unique ID.
	 * 
	 * @param hotelId The ID of the hotel to be retrieved.
	 * @return The hotel details if found, otherwise returns NOT FOUND status.
	 */
	@GetMapping("/{hotelId}")
	public ResponseEntity<Object> getHotelById(@PathVariable Long hotelId) {
		try {
			// Fetch hotel details based on hotel ID
			Optional<Hotel> hotel = hotelRepository.findById(hotelId);

			// If the hotel is found, return it with OK status
			if (hotel.isPresent()) {
				return ResponseEntity.ok(hotel.get());
			} else {
				// If hotel doesn't exist, return NOT FOUND status
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found");
			}
		} catch (Exception e) {
			// Handles unexpected errors and returns INTERNAL SERVER ERROR status
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching hotel: " + e.getMessage());
		}
	}
}
