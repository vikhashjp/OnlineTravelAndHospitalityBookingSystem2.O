package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "payment") // Explicitly defining the table name
public class Payment {

	@Id // Defines the primary key for the payment entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique payment IDs
	@Column(name = "id") // Maps to "id" in the database
	private Long paymentId;

	@Column(name = "amount", nullable = false) // Specifies the database column name
	@NotNull(message = "Amount is required") // Ensures that an amount is provided
	@Positive(message = "Amount must be positive") // Enforces non-negative values
	private Double amount; // Payment amount for the transaction

	@Column(name = "booking_id", nullable = false)
	@NotNull(message = "Booking ID is required") // Validates the presence of a booking ID
	private Long bookingId; // ID of the booking linked to the payment

	@Column(name = "payment_method", nullable = false)
	@NotBlank(message = "Payment method is required") // Ensures payment method is provided
	@Pattern(regexp = "Credit Card|Debit Card|Net Banking|UPI|Cash", message = "Invalid payment method") // Enforces
																											// valid
																											// payment
																											// methods
	private String paymentMethod; // Payment method used (e.g., Credit Card, UPI, Cash)

	@Column(name = "status", nullable = false)
	@NotBlank(message = "Payment status is required") // Ensures payment status is provided
	@Pattern(regexp = "Pending|Completed|Failed|Refunded", message = "Invalid payment status") // Restricts payment
																								// statuses to
																								// predefined values
	private String status; // Status of the payment (e.g., Pending, Completed)

	@Column(name = "user_id", nullable = false)
	@NotNull(message = "User ID is required") // Ensures a user ID is provided
	private Long userId; // ID of the user associated with this payment

	// Getters and Setters

	/**
	 * Retrieves the unique payment ID.
	 * 
	 * @return The payment ID.
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the unique payment ID.
	 * 
	 * @param paymentId The payment ID.
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Retrieves the payment amount.
	 * 
	 * @return The transaction amount.
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Sets the payment amount.
	 * 
	 * @param amount The transaction amount.
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * Retrieves the booking ID associated with the payment.
	 * 
	 * @return The booking ID.
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the booking ID for the payment.
	 * 
	 * @param bookingId The booking ID associated with the transaction.
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Retrieves the payment method used.
	 * 
	 * @return The method of payment.
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets the payment method used.
	 * 
	 * @param paymentMethod The method of payment (Credit Card, UPI, etc.).
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Retrieves the payment status.
	 * 
	 * @return The status of the payment transaction.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the payment status.
	 * 
	 * @param status The current status of the payment (Pending, Completed, etc.).
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the user ID associated with the payment.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the payment.
	 * 
	 * @param userId The user ID linked to the payment.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
