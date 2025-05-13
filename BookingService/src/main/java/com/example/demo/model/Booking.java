package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents a booking entity in the database.
 */
@Entity
@Table(name = "booking") // Defines the table name for this entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
	private Long bookingId;

	@NotNull(message = "User ID is required") // Ensures a valid user ID is provided
	private Long userId;

	@NotNull(message = "Package ID is required") // Ensures a valid package ID is provided
	private Long packageId;

	@NotNull(message = "Booking Type is required") // Specifies whether the booking is for a hotel or flight
	private String type; // "Hotel" or "Flight"

	private String status; // Stores the current status of the booking (Pending, Confirmed, Cancelled)
	private Long hotelId; // Stores the associated hotel ID (if applicable)
	private Long flightId; // Stores the associated flight ID (if applicable)

	private Integer numberOfRooms; // Required for hotel bookings
	private Integer numberOfSeats; // Required for flight bookings
	private Date bookingDate; // Stores the date of booking

	/**
	 * Default constructor initializes default values. Sets status to "Pending" and
	 * assigns the current date as the booking date.
	 */
	public Booking() {
		this.status = "Pending";
		this.bookingDate = new Date();
	}

	// Getters and Setters

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
}
