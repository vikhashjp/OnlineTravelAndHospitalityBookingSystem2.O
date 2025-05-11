package com.example.demo.dto;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) for managing invoice details. Used to transfer
 * invoice-related data between layers of the application.
 */
public class InvoiceDTO {

	private Long invoiceId; // Unique identifier for the invoice
	private Long bookingId; // ID of the booking associated with this invoice
	private Long userId; // ID of the user linked to the invoice
	private Double totalAmount; // Total amount for the invoice
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
