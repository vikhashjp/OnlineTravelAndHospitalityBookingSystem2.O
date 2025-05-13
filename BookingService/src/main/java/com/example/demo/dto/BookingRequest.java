package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for creating a booking request. Ensures all
 * required fields are validated before processing the booking.
 */
public class BookingRequest {

	@NotNull(message = "User ID is required") // Validates that user ID is not null
	private Long userId;

	@NotNull(message = "Package ID is required") // Ensures package ID is provided
	private Long packageId;

	@NotNull(message = "Booking Type is required") // Specifies whether the booking is for a hotel or flight
	private String type; // "Hotel" or "Flight"

	private Long hotelId; // Stores the associated hotel ID (if applicable)
	private Long flightId; // Stores the associated flight ID (if applicable)
	private Integer numberOfRooms; // Required for hotel bookings
	private Integer numberOfSeats; // Required for flight bookings

	// Getters and Setters

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
}
