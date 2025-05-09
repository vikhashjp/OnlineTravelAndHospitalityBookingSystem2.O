package com.example.demo.service;

import com.example.demo.dto.ItineraryDTO;
import java.util.List;

public interface ItineraryService {
    void createItinerary(ItineraryDTO itineraryDTO);
    List<ItineraryDTO> getAllItineraries();
}
