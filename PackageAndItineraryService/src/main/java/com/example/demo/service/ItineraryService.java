package com.example.demo.service;

import com.example.demo.dto.ItineraryDTO;
import java.util.List;

/**
 * Service interface for managing itinerary operations. Defines methods for
 * creating and retrieving itineraries.
 */
public interface ItineraryService {

	/**
	 * Creates a new itinerary entry.
	 * 
	 * @param itineraryDTO The itinerary details provided by the user.
	 */
	void createItinerary(ItineraryDTO itineraryDTO);

	/**
	 * Retrieves all stored itineraries.
	 * 
	 * @return A list of itineraries.
	 */
	List<ItineraryDTO> getAllItineraries();
}
