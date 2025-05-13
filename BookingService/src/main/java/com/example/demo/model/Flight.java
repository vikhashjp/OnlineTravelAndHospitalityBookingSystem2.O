package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Represents a Flight entity in the database. Contains flight details such as
 * airline, departure, arrival, price, and availability.
 */
@Entity
@Table(name = "flight") // Maps this entity to the "flight" table
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the flight ID
	private Long flightId;

	@Column(nullable = false)
	@NotBlank(message = "Airline name is required") // Ensures airline name is not empty
	private String airline;

	@Column(nullable = false)
	@NotBlank(message = "Departure location is required") // Ensures departure is provided
	private String departure;

	@Column(nullable = false)
	@NotBlank(message = "Arrival location is required") // Ensures arrival is provided
	private String arrival;

	@Column(nullable = false)
	@DecimalMin(value = "0.0", message = "Price must be non-negative") // Validates price to be non-negative
	private Double price;

	@Column(nullable = false)
	@Min(value = 0, message = "Availability must be non-negative") // Ensures number of available seats is non-negative
	private Integer availability;

	// Getters and Setters

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
}
