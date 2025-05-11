package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.PackageDTO;
import com.example.demo.model.Package;
import com.example.demo.repository.PackageRepository;
import com.example.demo.service.PackageService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Marks this class as a Spring Boot test configuration
class PackageAndItineraryServiceApplicationTests {

	@Mock // Creates a mock instance of PackageRepository for testing
	private PackageRepository packageRepository;

	@InjectMocks // Injects the mocked repository into PackageService
	private PackageService packageService;

	/**
	 * Tests the package creation functionality. Ensures that a package is correctly
	 * saved in the repository.
	 */
	@Test
	void testCreatePackage() {
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setName("Holiday Special");
		packageDTO.setIncludedHotels(Arrays.asList("Hotel1", "Hotel2"));
		packageDTO.setIncludedFlights(Arrays.asList("Flight1", "Flight2"));
		packageDTO.setActivities(Arrays.asList("Sightseeing", "Scuba Diving"));
		packageDTO.setPrice(1500.0);

		// Mocking package entity behavior
		Package packageEntity = new Package();
		packageEntity.setId(1L);
		packageEntity.setName(packageDTO.getName());
		packageEntity.setIncludedHotels(packageDTO.getIncludedHotels());
		packageEntity.setIncludedFlights(packageDTO.getIncludedFlights());
		packageEntity.setActivities(packageDTO.getActivities());
		packageEntity.setPrice(packageDTO.getPrice());

		Mockito.when(packageRepository.save(any(Package.class))).thenReturn(packageEntity);

		// Execute service method
		packageService.createPackage(packageDTO);

		// Verify that the repository method was called once
		verify(packageRepository, times(1)).save(any(Package.class));
	}

	/**
	 * Tests the retrieval of all packages from the database. Ensures correct data
	 * conversion and validation.
	 */
	@Test
	void testGetAllPackages() {
		Package package1 = new Package();
		package1.setId(1L);
		package1.setName("Beach Getaway");
		package1.setIncludedHotels(Arrays.asList("Hotel A", "Hotel B"));
		package1.setIncludedFlights(Arrays.asList("Flight X", "Flight Y"));
		package1.setActivities(Arrays.asList("Snorkeling", "Sunset Cruise"));
		package1.setPrice(2000.0);

		Package package2 = new Package();
		package2.setId(2L);
		package2.setName("Mountain Retreat");
		package2.setIncludedHotels(Arrays.asList("Cabin1", "Cabin2"));
		package2.setIncludedFlights(Arrays.asList("Flight P", "Flight Q"));
		package2.setActivities(Arrays.asList("Hiking", "Campfire"));
		package2.setPrice(2500.0);

		List<Package> packages = Arrays.asList(package1, package2);

		// Mock repository behavior
		Mockito.when(packageRepository.findAll()).thenReturn(packages);

		// Execute service method
		List<PackageDTO> packageDTOs = packageService.getAllPackages();

		// Assertions to verify correct retrieval
		assertNotNull(packageDTOs);
		assertEquals(2, packageDTOs.size());
		assertEquals("Beach Getaway", packageDTOs.get(0).getName());

		// Verify repository interaction
		verify(packageRepository, times(1)).findAll();
	}
}
