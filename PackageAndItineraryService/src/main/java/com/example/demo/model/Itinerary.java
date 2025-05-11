package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "itinerary") // Explicitly defining table name
public class Itinerary {

	@Id // Defines the primary key for the itinerary entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique itinerary IDs
	private Long id;

	@Column(nullable = false) // Ensures this field cannot be null in the database
	@NotNull(message = "User ID is required") // Validates that a user ID is provided
	private Long userId; // ID of the user associated with this itinerary

	@Column(nullable = false)
	@NotNull(message = "Package ID is required") // Validates that a package ID is provided
	private Long packageId; // ID of the travel package linked to this itinerary

	@Column(length = 500) // Limits customization details to 500 characters
	@Size(max = 500, message = "Customization details cannot exceed 500 characters") // Ensures content length
																						// restriction
	private String customizationDetails; // Customization preferences for the itinerary

	// Getters and Setters

	/**
	 * Retrieves the unique itinerary ID.
	 * 
	 * @return The itinerary ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique itinerary ID.
	 * 
	 * @param id The itinerary ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the user ID associated with the itinerary.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the itinerary.
	 * 
	 * @param userId The user ID linked to the itinerary.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the package ID linked to the itinerary.
	 * 
	 * @return The package ID.
	 */
	public Long getPackageId() {
		return packageId;
	}

	/**
	 * Sets the package ID for the itinerary.
	 * 
	 * @param packageId The ID of the linked travel package.
	 */
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	/**
	 * Retrieves the customization details for the itinerary.
	 * 
	 * @return Customization preferences.
	 */
	public String getCustomizationDetails() {
		return customizationDetails;
	}

	/**
	 * Sets the customization details for the itinerary.
	 * 
	 * @param customizationDetails The user's customization preferences.
	 */
	public void setCustomizationDetails(String customizationDetails) {
		this.customizationDetails = customizationDetails;
	}
}
