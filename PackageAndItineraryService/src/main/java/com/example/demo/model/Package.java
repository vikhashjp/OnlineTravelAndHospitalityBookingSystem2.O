package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "package") // Explicitly defining the table name
public class Package {

	@Id // Defines the primary key for the package entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique package IDs
	private Long id;

	@Column(nullable = false, length = 100) // Ensures the column is required and limits its length
	@NotBlank(message = "Package name is required") // Validation: Cannot be blank
	@Size(max = 100, message = "Package name must not exceed 100 characters") // Restricts length
	private String name; // Name of the travel package

	@ElementCollection // Specifies that this is a collection of embedded values
	@NotEmpty(message = "At least one hotel must be included in the package") // Ensures non-empty hotel list
	private List<String> includedHotels; // List of hotels included in the package

	@ElementCollection
	@NotEmpty(message = "At least one flight must be included in the package") // Ensures non-empty flight list
	private List<String> includedFlights; // List of flights included in the package

	@ElementCollection
	@NotEmpty(message = "At least one activity must be included in the package") // Ensures non-empty activity list
	private List<String> activities; // List of activities available in the package

	@Column(nullable = false)
	@NotNull(message = "Package price is required") // Ensures the price is provided
	@Positive(message = "Price must be positive") // Enforces non-negative pricing
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
