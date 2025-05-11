package com.example.demo.controller;

import com.example.demo.dto.PackageDTO;
import com.example.demo.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/packages") // Defines the base path for package-related endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to interact with this API
public class PackageController {

	@Autowired // Injects the PackageService to handle business logic
	private PackageService packageService;

	/**
	 * Creates a new package entry in the database.
	 * 
	 * @param packageDTO The package details provided in the request body.
	 * @return A response entity with CREATED status if successful, or BAD REQUEST
	 *         if an error occurs.
	 */
	@PostMapping
	public ResponseEntity<String> createPackage(@Valid @RequestBody PackageDTO packageDTO) {
		try {
			packageService.createPackage(packageDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Package created successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create package: " + e.getMessage());
		}
	}

	/**
	 * Retrieves all packages from the database.
	 * 
	 * @return A list of packages or an error message if retrieval fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllPackages() {
		try {
			List<PackageDTO> packages = packageService.getAllPackages();

			// If no packages exist, return NO CONTENT status
			if (packages.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No packages found");
			}

			// Return the list of packages with OK status
			return ResponseEntity.ok(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching packages: " + e.getMessage());
		}
	}
}
