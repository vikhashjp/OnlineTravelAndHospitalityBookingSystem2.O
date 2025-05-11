package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) representing a booking request. This class is used
 * to pass booking details between the client and backend.
 */
public class BookingRequest {

	private Long userId; // ID of the user making the booking
	private String type; // Booking type: "Hotel" or "Flight"
	private Long paymentId; // Associated payment ID for the booking
	private Long hotelId; // Optional: Hotel ID for hotel bookings
	private Long flightId; // Optional: Flight ID for flight bookings

	// Getters and Setters

	/**
	 * Retrieves the user ID associated with the booking.
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the booking.
	 * 
	 * @param userId ID of the user making the booking
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the type of booking (Hotel or Flight).
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of booking.
	 * 
	 * @param type The booking type, should be either "Hotel" or "Flight"
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieves the payment ID associated with the booking.
	 * 
	 * @return paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the payment ID for the booking.
	 * 
	 * @param paymentId The ID associated with the payment transaction
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Retrieves the hotel ID if the booking is for a hotel.
	 * 
	 * @return hotelId
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the hotel ID for the booking.
	 * 
	 * @param hotelId The ID of the hotel being booked (optional)
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Retrieves the flight ID if the booking is for a flight.
	 * 
	 * @return flightId
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * Sets the flight ID for the booking.
	 * 
	 * @param flightId The ID of the flight being booked (optional)
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
}
