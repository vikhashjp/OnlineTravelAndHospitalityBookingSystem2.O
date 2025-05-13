package com.example.demo.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for package details. Represents travel packages
 * that include hotels, flights, activities, and pricing.
 */
public class PackageDTO {

	private Long id; // Unique identifier for the package
	private String name; // Name of the package (e.g., "Luxury Beach Holiday")
	private List<String> includedHotels; // List of hotel names included in the package
	private List<String> includedFlights; // List of flight details included in the package
	private List<String> activities; // List of activities provided in the package
	private Double price; // Price of the package

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getIncludedHotels() {
		return includedHotels;
	}

	public void setIncludedHotels(List<String> includedHotels) {
		this.includedHotels = includedHotels;
	}

	public List<String> getIncludedFlights() {
		return includedFlights;
	}

	public void setIncludedFlights(List<String> includedFlights) {
		this.includedFlights = includedFlights;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setActivities(List<String> activities) {
		this.activities = activities;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
