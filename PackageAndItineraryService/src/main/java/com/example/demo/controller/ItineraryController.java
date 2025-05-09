package com.example.demo.controller;

import com.example.demo.dto.ItineraryDTO;
import com.example.demo.service.ItineraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<String> createItinerary(@Valid @RequestBody ItineraryDTO itineraryDTO) {
        try {
            itineraryService.createItinerary(itineraryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Itinerary created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create itinerary: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllItineraries() {
        try {
            List<ItineraryDTO> itineraries = itineraryService.getAllItineraries();
            if (itineraries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No itineraries found");
            }
            return ResponseEntity.ok(itineraries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching itineraries: " + e.getMessage());
        }
    }
}
