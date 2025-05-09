package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Hotel name is required")
    @Size(max = 100, message = "Hotel name must not exceed 100 characters")
    private String name;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;

    @Column(nullable = false)
    @Min(value = 0, message = "Rooms available cannot be negative")
    private int roomsAvailable;

    @Column(nullable = false)
    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private double rating;

    @Column(nullable = false)
    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be positive")
    private double pricePerNight;

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

	public int getRoomsAvailable() {
		return roomsAvailable;
	}

	public void setRoomsAvailable(int roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

    // Getters and Setters
    
}
