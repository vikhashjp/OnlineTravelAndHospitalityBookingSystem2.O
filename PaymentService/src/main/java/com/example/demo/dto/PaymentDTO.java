package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) for managing payment details. Used to transfer
 * payment-related data between layers of the application.
 */
public class PaymentDTO {

	private Long paymentId; // Unique identifier for the payment transaction
	private Long userId; // ID of the user associated with this payment
	private Long bookingId; // ID of the booking linked to this payment
	private Double amount; // Payment amount for the transaction
	private String status; // Payment status (e.g., Pending, Completed, Failed)
	private String paymentMethod; // Payment method used (e.g., Credit Card, PayPal, Bank Transfer)

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

	/**
	 * Retrieves the booking ID linked to the payment.
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
	 * @param paymentMethod The method of payment (Credit Card, PayPal, etc.).
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
