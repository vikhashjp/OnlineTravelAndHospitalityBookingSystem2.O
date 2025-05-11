package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "invoice") // Explicitly defining the table name in the database
public class Invoice {

	@Id // Defines the primary key for the invoice entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique invoice IDs
	private Long invoiceId;

	@Column(name = "booking_id", nullable = false) // Maps the field to a database column
	@NotNull(message = "Booking ID is required") // Ensures the booking ID is provided
	private Long bookingId; // ID of the booking associated with the invoice

	@Column(name = "user_id", nullable = false)
	@NotNull(message = "User ID is required") // Validates presence of user ID
	private Long userId; // ID of the user linked to the invoice

	@Column(name = "total_amount", nullable = false)
	@NotNull(message = "Total amount is required") // Ensures the total amount is provided
	@Positive(message = "Total amount must be positive") // Enforces non-negative values
	private Double totalAmount; // Total amount for the invoice

	@Column(name = "timestamp", nullable = false)
	@PastOrPresent(message = "Timestamp cannot be in the future") // Ensures valid historical timestamps
	private LocalDateTime timestamp; // Timestamp of invoice generation

	// Getters and Setters

	/**
	 * Retrieves the unique invoice ID.
	 * 
	 * @return The invoice ID.
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * Sets the unique invoice ID.
	 * 
	 * @param invoiceId The invoice ID.
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * Retrieves the booking ID associated with the invoice.
	 * 
	 * @return The booking ID.
	 */
	public Long getBookingId() {
		return bookingId;
	}

	/**
	 * Sets the booking ID for the invoice.
	 * 
	 * @param bookingId The ID of the related booking.
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * Retrieves the user ID linked to the invoice.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the invoice.
	 * 
	 * @param userId The ID of the user.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the total amount of the invoice.
	 * 
	 * @return The total amount.
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets the total amount for the invoice.
	 * 
	 * @param totalAmount The amount to be stored.
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * Retrieves the timestamp when the invoice was generated.
	 * 
	 * @return The timestamp.
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp for the invoice.
	 * 
	 * @param timestamp The timestamp of invoice generation.
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
