package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "flight") // Specifies the table name for this entity
public class Flight {

	@Id // Primary key for the flight entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique flight IDs
	@Column(name = "flight_id") // Maps to the "flight_id" column in the database
	private Long flightId;

	@Column(nullable = false, length = 100) // Ensures the column is required and limits its length
	@NotBlank(message = "Airline name is required") // Validation: Cannot be blank
	@Size(max = 100, message = "Airline name must not exceed 100 characters") // Restricts length
	private String airline; // Name of the airline operating the flight

	@Column(nullable = false, length = 50)
	@NotBlank(message = "Departure location is required") // Validation: Cannot be blank
	@Size(max = 50, message = "Departure location must not exceed 50 characters") // Restricts length
	private String departure; // Flight departure location

	@Column(nullable = false, length = 50)
	@NotBlank(message = "Arrival location is required") // Validation: Cannot be blank
	@Size(max = 50, message = "Arrival location must not exceed 50 characters") // Restricts length
	private String arrival; // Flight arrival location

	@Column(nullable = false)
	@NotNull(message = "Price is required") // Validation: Must be provided
	@Positive(message = "Price must be positive") // Ensures flight price is a positive value
	private double price; // Cost of the flight ticket

	@Column(nullable = false)
	@Min(value = 0, message = "Availability cannot be negative") // Ensures availability is non-negative
	private int availability; // Number of available seats on the flight

	// Getters and Setters

	/**
	 * Retrieves the flight ID.
	 * 
	 * @return The unique flight ID.
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight ID.
	 * 
	 * @param flightId The unique flight ID.
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	/**
	 * Retrieves the airline name.
	 * 
	 * @return The airline name.
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets the airline name.
	 * 
	 * @param airline The name of the airline operating the flight.
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Retrieves the departure location.
	 * 
	 * @return The departure location.
	 */
	public String getDeparture() {
		return departure;
	}

	/**
	 * Sets the departure location.
	 * 
	 * @param departure The departure location of the flight.
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}

	/**
	 * Retrieves the arrival location.
	 * 
	 * @return The arrival location.
	 */
	public String getArrival() {
		return arrival;
	}

	/**
	 * Sets the arrival location.
	 * 
	 * @param arrival The arrival location of the flight.
	 */
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	/**
	 * Retrieves the price of the flight.
	 * 
	 * @return The flight price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price for the flight.
	 * 
	 * @param price The cost of the flight ticket.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Retrieves the number of available seats on the flight.
	 * 
	 * @return The availability count.
	 */
	public int getAvailability() {
		return availability;
	}

	/**
	 * Sets the number of available seats.
	 * 
	 * @param availability The number of seats available on the flight.
	 */
	public void setAvailability(int availability) {
		this.availability = availability;
	}
}
