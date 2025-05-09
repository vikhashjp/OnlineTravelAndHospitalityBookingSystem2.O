package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Airline name is required")
    @Size(max = 100, message = "Airline name must not exceed 100 characters")
    private String airline;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Departure location is required")
    @Size(max = 50, message = "Departure location must not exceed 50 characters")
    private String departure;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Arrival location is required")
    @Size(max = 50, message = "Arrival location must not exceed 50 characters")
    private String arrival;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private double price;

    @Column(nullable = false)
    @Min(value = 0, message = "Availability cannot be negative")
    private int availability;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
