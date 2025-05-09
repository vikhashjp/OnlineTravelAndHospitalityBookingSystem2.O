package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false)
    @NotNull(message = "User ID is required")
    private Long userId;

    @Column(nullable = false)
    @NotBlank(message = "Booking type is required")
    @Pattern(regexp = "Hotel|Flight", message = "Booking type must be 'Hotel' or 'Flight'")
    private String type; // Hotel or Flight

    @Column(nullable = false)
    @NotBlank(message = "Booking status is required")
    @Pattern(regexp = "Pending|Confirmed|Cancelled", message = "Status must be 'Pending', 'Confirmed', or 'Cancelled'")
    private String status;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "flight_id")
    private Long flightId;

    @Min(value = 1, message = "Number of rooms must be at least 1")
    private int numberOfRooms;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @PastOrPresent(message = "Booking date cannot be in the future")
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", insertable = false, updatable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false)
    private Flight flight;

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

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

    // Getters and Setters
    
}
