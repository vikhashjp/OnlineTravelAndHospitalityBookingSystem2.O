package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) for managing itinerary details. Used to transfer
 * itinerary-related data between layers of the application.
 */
public class ItineraryDTO {

	private Long id; // Unique identifier for the itinerary
	private Long userId; // ID of the user associated with this itinerary
	private Long packageId; // ID of the travel package linked to this itinerary
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
