package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Represents a Hotel entity in the database. Stores details like hotel name,
 * location, availability, rating, and pricing.
 */
@Entity
@Table(name = "hotel") // Maps this entity to the "hotel" table
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the hotel ID
	private Long hotelId;

	@Column(nullable = false)
	@NotBlank(message = "Hotel name is required") // Ensures hotel name is not empty
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Location is required") // Ensures location is provided
	private String location;

	@Column(nullable = false)
	@Min(value = 1, message = "Rooms must be at least 1") // Validates minimum number of available rooms
	private Integer roomsAvailable;

	@Column(nullable = false)
	@DecimalMin(value = "0.0", message = "Rating must be non-negative") // Ensures rating is non-negative
	private Double rating;

	@Column(nullable = false)
	@DecimalMin(value = "0.0", message = "Price must be non-negative") // Ensures price per night is non-negative
	private Double pricePerNight;

	// Getters and Setters

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getRoomsAvailable() {
		return roomsAvailable;
	}

	public void setRoomsAvailable(Integer roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
}
