package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "payment") // Ensure it maps to the correct table name
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;

    @Column(name = "amount", nullable = false)
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @Column(name = "booking_id", nullable = false)
    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @Column(name = "payment_method", nullable = false)
    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "Credit Card|Debit Card|Net Banking|UPI|Cash", message = "Invalid payment method")
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Payment status is required")
    @Pattern(regexp = "Pending|Completed|Failed|Refunded", message = "Invalid payment status")
    private String status;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User ID is required")
    private Long userId;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    // Getters and Setters
    
}
