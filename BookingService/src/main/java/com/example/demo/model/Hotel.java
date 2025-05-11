package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "hotel") // Specifies the table name for this entity
public class Hotel {

	@Id // Defines the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique hotel IDs
	@Column(name = "hotel_id") // Maps to "hotel_id" column in the database
	private Long hotelId;

	@Column(nullable = false, length = 100) // Ensures the column is required and limits length
	@NotBlank(message = "Hotel name is required") // Validation: Cannot be blank
	@Size(max = 100, message = "Hotel name must not exceed 100 characters") // Restricts length
	private String name; // Name of the hotel

	@Column(nullable = false, length = 100)
	@NotBlank(message = "Location is required") // Validation: Cannot be blank
	@Size(max = 100, message = "Location must not exceed 100 characters") // Restricts length
	private String location; // Address or city of the hotel

	@Column(nullable = false)
	@Min(value = 0, message = "Rooms available cannot be negative") // Ensures non-negative room availability
	private int roomsAvailable; // Number of available rooms in the hotel

	@Column(nullable = false)
	@Min(value = 0, message = "Rating cannot be negative") // Ensures rating isn't below 0
	@Max(value = 5, message = "Rating must be between 0 and 5") // Restricts rating range
	private double rating; // Average customer rating for the hotel

	@Column(nullable = false)
	@NotNull(message = "Price per night is required") // Ensures price per night is provided
	@Positive(message = "Price per night must be positive") // Ensures price isn't negative
	private double pricePerNight; // Cost per night for a hotel stay

	// Getters and Setters

	/**
	 * Retrieves the hotel ID.
	 * 
	 * @return The unique hotel ID.
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the hotel ID.
	 * 
	 * @param hotelId The unique hotel ID.
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Retrieves the hotel name.
	 * 
	 * @return The hotel name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the hotel name.
	 * 
	 * @param name The name of the hotel.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the hotel location.
	 * 
	 * @return The hotel location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the hotel location.
	 * 
	 * @param location The address or city where the hotel is located.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Retrieves the number of available rooms.
	 * 
	 * @return The available rooms count.
	 */
	public int getRoomsAvailable() {
		return roomsAvailable;
	}

	/**
	 * Sets the number of available rooms.
	 * 
	 * @param roomsAvailable The count of available rooms.
	 */
	public void setRoomsAvailable(int roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	/**
	 * Retrieves the hotel rating.
	 * 
	 * @return The hotel's rating.
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Sets the hotel rating.
	 * 
	 * @param rating The rating of the hotel (0-5 scale).
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Retrieves the price per night for a stay.
	 * 
	 * @return The price per night.
	 */
	public double getPricePerNight() {
		return pricePerNight;
	}

	/**
	 * Sets the price per night.
	 * 
	 * @param pricePerNight The cost of staying per night at the hotel.
	 */
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
}
