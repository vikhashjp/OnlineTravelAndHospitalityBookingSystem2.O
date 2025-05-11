package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a Spring REST controller handling HTTP requests
@RequestMapping("/api/flights") // Defines the base path for flight-related endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to access this API
public class FlightController {

	@Autowired // Injects the FlightRepository dependency to interact with the database
	private FlightRepository flightRepository;

	/**
	 * Creates a new flight entry in the database.
	 * 
	 * @param flight The flight object provided in the request body.
	 * @return The saved flight object with HTTP status CREATED or an error message
	 *         if creation fails.
	 */
	@PostMapping
	public ResponseEntity<Object> createFlight(@Valid @RequestBody Flight flight) {
		try {
			// Saves flight information to the database
			Flight savedFlight = flightRepository.save(flight);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
		} catch (Exception e) {
			// Handles errors and returns BAD REQUEST status
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create flight: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all flights from the database.
	 * 
	 * @return A list of flights or an error message if the database retrieval
	 *         fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllFlights() {
		try {
			List<Flight> flights = flightRepository.findAll();

			// If no flights exist, return NO CONTENT status
			if (flights.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No flights found");
			}

			// Return the list of flights with OK status
			return ResponseEntity.ok(flights);
		} catch (Exception e) {
			// Handles unexpected errors and returns INTERNAL SERVER ERROR status
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching flights: " + e.getMessage());
		}
	}

	/**
	 * Retrieves a flight by its unique ID.
	 * 
	 * @param flightId The ID of the flight to be retrieved.
	 * @return The flight details if found, otherwise returns NOT FOUND status.
	 */
	@GetMapping("/{flightId}")
	public ResponseEntity<Object> getFlightById(@PathVariable Long flightId) {
		try {
			// Fetch flight details based on flight ID
			Optional<Flight> flight = flightRepository.findById(flightId);

			// If the flight is found, return it with OK status
			if (flight.isPresent()) {
				return ResponseEntity.ok(flight.get());
			} else {
				// If flight doesn't exist, return NOT FOUND status
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
			}
		} catch (Exception e) {
			// Handles unexpected errors and returns INTERNAL SERVER ERROR status
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching flight: " + e.getMessage());
		}
	}
}
