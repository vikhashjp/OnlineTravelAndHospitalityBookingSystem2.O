package com.example.demo.service;

import com.example.demo.dto.PackageDTO;
import com.example.demo.exceptions.PackageNotFoundException;
import com.example.demo.model.Package;
import com.example.demo.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Marks this class as a Spring-managed service component
public class PackageServiceImpl implements PackageService {

	private static final Logger log = LoggerFactory.getLogger(PackageServiceImpl.class); // Logger for debugging

	@Autowired // Injects Package repository to interact with the database
	private PackageRepository packageRepository;

	/**
	 * Creates a new package entry in the database.
	 * 
	 * @param packageDTO The package details provided by the user.
	 */
	@Override
	public void createPackage(PackageDTO packageDTO) {
		log.info("Creating package: {}", packageDTO.getName());

		// Convert DTO to entity
		Package packageEntity = new Package();
		packageEntity.setName(packageDTO.getName());
		packageEntity.setIncludedHotels(packageDTO.getIncludedHotels());
		packageEntity.setIncludedFlights(packageDTO.getIncludedFlights());
		packageEntity.setActivities(packageDTO.getActivities());
		packageEntity.setPrice(packageDTO.getPrice());

		packageRepository.save(packageEntity);
		log.info("Package created successfully: {}", packageEntity);
	}

	/**
	 * Retrieves all stored packages from the database.
	 * 
	 * @return A list of package DTOs.
	 * @throws PackageNotFoundException if no packages exist.
	 */
	@Override
	public List<PackageDTO> getAllPackages() {
		log.info("Fetching all packages...");
		List<Package> packages = packageRepository.findAll();

		// Check if packages exist
		if (packages.isEmpty()) {
			log.error("No packages found.");
			throw new PackageNotFoundException("No packages found.");
		}

		// Convert entity list to DTO list
		List<PackageDTO> packageDTOs = new ArrayList<>();
		for (Package packageEntity : packages) {
			PackageDTO dto = new PackageDTO();
			dto.setId(packageEntity.getId());
			dto.setName(packageEntity.getName());
			dto.setIncludedHotels(packageEntity.getIncludedHotels());
			dto.setIncludedFlights(packageEntity.getIncludedFlights());
			dto.setActivities(packageEntity.getActivities());
			dto.setPrice(packageEntity.getPrice());
			packageDTOs.add(dto);
		}

		log.info("Total packages fetched: {}", packageDTOs.size());
		return packageDTOs;
	}
}
