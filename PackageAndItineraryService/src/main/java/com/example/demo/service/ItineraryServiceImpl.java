package com.example.demo.service;

import com.example.demo.dto.ItineraryDTO;
import com.example.demo.model.Itinerary;
import com.example.demo.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public void createItinerary(ItineraryDTO itineraryDTO) {
        Itinerary itinerary = new Itinerary();
        itinerary.setUserId(itineraryDTO.getUserId());
        itinerary.setPackageId(itineraryDTO.getPackageId());
        itinerary.setCustomizationDetails(itineraryDTO.getCustomizationDetails());
        itineraryRepository.save(itinerary);
    }

    @Override
    public List<ItineraryDTO> getAllItineraries() {
        List<Itinerary> itineraries = itineraryRepository.findAll();
        List<ItineraryDTO> itineraryDTOs = new ArrayList<>();

        for (Itinerary itinerary : itineraries) {
            ItineraryDTO dto = new ItineraryDTO();
            dto.setId(itinerary.getId());
            dto.setUserId(itinerary.getUserId());
            dto.setPackageId(itinerary.getPackageId());
            dto.setCustomizationDetails(itinerary.getCustomizationDetails());
            itineraryDTOs.add(dto);
        }
        return itineraryDTOs;
    }
}
