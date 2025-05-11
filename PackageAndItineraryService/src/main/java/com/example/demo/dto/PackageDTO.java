package com.example.demo.dto;

import java.util.List;

/**
 * DTO (Data Transfer Object) for managing package details. Used to transfer
 * package-related data between layers of the application.
 */
public class PackageDTO {

	private Long id; // Unique identifier for the travel package
	private String name; // Name of the travel package
	private List<String> includedHotels; // List of hotels included in the package
	private List<String> includedFlights; // List of flights included in the package
	private List<String> activities; // List of activities available in the package
	private Double price; // Total price for the package

	// Getters and Setters

	/**
	 * Retrieves the unique package ID.
	 * 
	 * @return The package ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique package ID.
	 * 
	 * @param id The package ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the package.
	 * 
	 * @return The package name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the package.
	 * 
	 * @param name The package name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the list of hotels included in the package.
	 * 
	 * @return A list of hotel names.
	 */
	public List<String> getIncludedHotels() {
		return includedHotels;
	}

	/**
	 * Sets the list of hotels included in the package.
	 * 
	 * @param includedHotels A list of hotel names.
	 */
	public void setIncludedHotels(List<String> includedHotels) {
		this.includedHotels = includedHotels;
	}

	/**
	 * Retrieves the list of flights included in the package.
	 * 
	 * @return A list of flight details.
	 */
	public List<String> getIncludedFlights() {
		return includedFlights;
	}

	/**
	 * Sets the list of flights included in the package.
	 * 
	 * @param includedFlights A list of flight details.
	 */
	public void setIncludedFlights(List<String> includedFlights) {
		this.includedFlights = includedFlights;
	}

	/**
	 * Retrieves the list of activities included in the package.
	 * 
	 * @return A list of activity names.
	 */
	public List<String> getActivities() {
		return activities;
	}

	/**
	 * Sets the list of activities included in the package.
	 * 
	 * @param activities A list of activity names.
	 */
	public void setActivities(List<String> activities) {
		this.activities = activities;
	}

	/**
	 * Retrieves the total price of the package.
	 * 
	 * @return The package price.
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the total price of the package.
	 * 
	 * @param price The package price.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
