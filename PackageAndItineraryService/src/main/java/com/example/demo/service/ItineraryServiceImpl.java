package com.example.demo.service;

import com.example.demo.dto.ItineraryDTO;
import com.example.demo.exceptions.ItineraryNotFoundException;
import com.example.demo.model.Itinerary;
import com.example.demo.repository.ItineraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Marks this class as a Spring-managed service component
public class ItineraryServiceImpl implements ItineraryService {

	private static final Logger log = LoggerFactory.getLogger(ItineraryServiceImpl.class); // Logger for debugging

	@Autowired // Injects Itinerary repository to interact with the database
	private ItineraryRepository itineraryRepository;

	/**
	 * Creates a new itinerary entry in the database.
	 * 
	 * @param itineraryDTO The itinerary details provided by the user.
	 */
	@Override
	public void createItinerary(ItineraryDTO itineraryDTO) {
		log.info("Creating itinerary for User ID: {}", itineraryDTO.getUserId());

		// Convert DTO to entity
		Itinerary itinerary = new Itinerary();
		itinerary.setUserId(itineraryDTO.getUserId());
		itinerary.setPackageId(itineraryDTO.getPackageId());
		itinerary.setCustomizationDetails(itineraryDTO.getCustomizationDetails());

		itineraryRepository.save(itinerary);
		log.info("Itinerary created successfully: {}", itinerary);
	}

	/**
	 * Retrieves all stored itineraries from the database.
	 * 
	 * @return A list of itinerary DTOs.
	 * @throws ItineraryNotFoundException if no itineraries exist.
	 */
	@Override
	public List<ItineraryDTO> getAllItineraries() {
		log.info("Fetching all itineraries...");
		List<Itinerary> itineraries = itineraryRepository.findAll();

		// Check if itineraries exist
		if (itineraries.isEmpty()) {
			log.error("No itineraries found.");
			throw new ItineraryNotFoundException("No itineraries found.");
		}

		// Convert entity list to DTO list
		List<ItineraryDTO> itineraryDTOs = new ArrayList<>();
		for (Itinerary itinerary : itineraries) {
			ItineraryDTO dto = new ItineraryDTO();
			dto.setId(itinerary.getId());
			dto.setUserId(itinerary.getUserId());
			dto.setPackageId(itinerary.getPackageId());
			dto.setCustomizationDetails(itinerary.getCustomizationDetails());
			itineraryDTOs.add(dto);
		}

		log.info("Total itineraries fetched: {}", itineraryDTOs.size());
		return itineraryDTOs;
	}
}
