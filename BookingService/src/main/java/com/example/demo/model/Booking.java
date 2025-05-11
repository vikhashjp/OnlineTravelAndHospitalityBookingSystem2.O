package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "booking") // Specifies the table name for this entity
public class Booking {

	@Id // Marks this field as the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique ID values
	private Long bookingId;

	@Column(nullable = false) // Ensures this field cannot be null in the database
	@NotNull(message = "User ID is required") // Validates that User ID is provided
	private Long userId;

	@Column(nullable = false)
	@NotBlank(message = "Booking type is required") // Validates that type is not blank
	@Pattern(regexp = "Hotel|Flight", message = "Booking type must be 'Hotel' or 'Flight'") // Restricts allowed values
	private String type; // Booking type: Hotel or Flight

	@Column(nullable = false)
	@NotBlank(message = "Booking status is required") // Ensures status is provided
	@Pattern(regexp = "Pending|Confirmed|Cancelled", message = "Status must be 'Pending', 'Confirmed', or 'Cancelled'") // Restricts
																														// allowed
																														// statuses
	private String status;

	@Column(name = "payment_id") // Maps to the "payment_id" column in the database
	private Long paymentId;

	@Column(name = "hotel_id") // Optional: Stores hotel ID for hotel bookings
	private Long hotelId;

	@Column(name = "flight_id") // Optional: Stores flight ID for flight bookings
	private Long flightId;

	@Min(value = 1, message = "Number of rooms must be at least 1") // Ensures valid room count
	private int numberOfRooms;

	@Temporal(TemporalType.TIMESTAMP) // Specifies that this field is a timestamp
	@Column(nullable = false)
	@PastOrPresent(message = "Booking date cannot be in the future") // Ensures booking date isn't in the future
	private Date bookingDate;

	// Establishes a many-to-one relationship with Hotel, linking by hotel_id
	@ManyToOne
	@JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", insertable = false, updatable = false)
	private Hotel hotel;

	// Establishes a many-to-one relationship with Flight, linking by flight_id
	@ManyToOne
	@JoinColumn(name = "flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false)
	private Flight flight;

	// Getters and Setters

	/**
	 * Retrieves the booking ID.
	 * 
	 * @return The unique booking ID.
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the booking ID.
	 * 
	 * @param bookingId The unique booking ID.
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Retrieves the user ID associated with the booking.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the booking.
	 * 
	 * @param userId The ID of the user making the booking.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the booking type (Hotel or Flight).
	 * 
	 * @return The booking type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the booking type.
	 * 
	 * @param type The type of booking ("Hotel" or "Flight").
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieves the current booking status.
	 * 
	 * @return The booking status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the booking status.
	 * 
	 * @param status The booking status ("Pending", "Confirmed", or "Cancelled").
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the payment ID associated with the booking.
	 * 
	 * @return The payment ID.
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the payment ID for the booking.
	 * 
	 * @param paymentId The ID of the payment transaction.
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Retrieves the hotel ID for hotel bookings.
	 * 
	 * @return The hotel ID.
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the hotel ID for the booking.
	 * 
	 * @param hotelId The ID of the booked hotel.
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Retrieves the flight ID for flight bookings.
	 * 
	 * @return The flight ID.
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight ID for the booking.
	 * 
	 * @param flightId The ID of the booked flight.
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	/**
	 * Retrieves the number of rooms booked.
	 * 
	 * @return The number of rooms.
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * Sets the number of rooms booked.
	 * 
	 * @param numberOfRooms The count of rooms booked.
	 */
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	/**
	 * Retrieves the booking date.
	 * 
	 * @return The booking date.
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * Sets the booking date.
	 * 
	 * @param bookingDate The date when the booking was made.
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	/**
	 * Retrieves the associated hotel entity.
	 * 
	 * @return The linked hotel entity.
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * Sets the associated hotel entity.
	 * 
	 * @param hotel The linked hotel entity.
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * Retrieves the associated flight entity.
	 * 
	 * @return The linked flight entity.
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * Sets the associated flight entity.
	 * 
	 * @param flight The linked flight entity.
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
