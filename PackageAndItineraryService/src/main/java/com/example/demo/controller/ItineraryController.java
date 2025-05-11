package com.example.demo.controller;

import com.example.demo.dto.ItineraryDTO;
import com.example.demo.service.ItineraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/itineraries") // Defines the base path for itinerary-related endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to interact with this API
public class ItineraryController {

	@Autowired // Injects the ItineraryService to handle business logic
	private ItineraryService itineraryService;

	/**
	 * Creates a new itinerary entry in the database.
	 * 
	 * @param itineraryDTO The itinerary details provided in the request body.
	 * @return A response entity with CREATED status if successful, or BAD REQUEST
	 *         if an error occurs.
	 */
	@PostMapping
	public ResponseEntity<String> createItinerary(@Valid @RequestBody ItineraryDTO itineraryDTO) {
		try {
			itineraryService.createItinerary(itineraryDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Itinerary created successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create itinerary: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all itineraries from the database.
	 * 
	 * @return A list of itineraries or an error message if retrieval fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllItineraries() {
		try {
			List<ItineraryDTO> itineraries = itineraryService.getAllItineraries();

			// If no itineraries exist, return NO CONTENT status
			if (itineraries.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No itineraries found");
			}

			// Return the list of itineraries with OK status
			return ResponseEntity.ok(itineraries);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching itineraries: " + e.getMessage());
		}
	}
}
