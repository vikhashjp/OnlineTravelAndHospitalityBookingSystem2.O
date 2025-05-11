package com.example.demo.service;

import com.example.demo.dto.PackageDTO;
import java.util.List;

/**
 * Service interface for managing package operations. Defines methods for
 * creating and retrieving packages.
 */
public interface PackageService {

	/**
	 * Creates a new package entry.
	 * 
	 * @param packageDTO The package details provided by the user.
	 */
	void createPackage(PackageDTO packageDTO);

	/**
	 * Retrieves all stored packages.
	 * 
	 * @return A list of package DTOs.
	 */
	List<PackageDTO> getAllPackages();
}
