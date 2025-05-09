package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "itinerary") // Explicitly defining table name
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "User ID is required")
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "Package ID is required")
    private Long packageId;

    @Column(length = 500)
    @Size(max = 500, message = "Customization details cannot exceed 500 characters")
    private String customizationDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getCustomizationDetails() {
		return customizationDetails;
	}

	public void setCustomizationDetails(String customizationDetails) {
		this.customizationDetails = customizationDetails;
	}

    // Getters and Setters
    
}
