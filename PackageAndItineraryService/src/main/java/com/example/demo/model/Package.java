package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "package") // Explicitly defining table name
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Package name is required")
    @Size(max = 100, message = "Package name must not exceed 100 characters")
    private String name;

    @ElementCollection
    @NotEmpty(message = "At least one hotel must be included in the package")
    private List<String> includedHotels;

    @ElementCollection
    @NotEmpty(message = "At least one flight must be included in the package")
    private List<String> includedFlights;

    @ElementCollection
    @NotEmpty(message = "At least one activity must be included in the package")
    private List<String> activities;

    @Column(nullable = false)
    @NotNull(message = "Package price is required")
    @Positive(message = "Price must be positive")
    private Double price;

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

    // Getters and Setters
    
}
