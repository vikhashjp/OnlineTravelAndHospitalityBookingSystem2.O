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

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping
    public ResponseEntity<Object> createFlight(@Valid @RequestBody Flight flight) {
        try {
            Flight savedFlight = flightRepository.save(flight);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create flight: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllFlights() {
        try {
            List<Flight> flights = flightRepository.findAll();
            if (flights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No flights found");
            }
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching flights: " + e.getMessage());
        }
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Object> getFlightById(@PathVariable Long flightId) {
        try {
            Optional<Flight> flight = flightRepository.findById(flightId);
            if (flight.isPresent()) {
                return ResponseEntity.ok(flight.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching flight: " + e.getMessage());
        }
    }
}
