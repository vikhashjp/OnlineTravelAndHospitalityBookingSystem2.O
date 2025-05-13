package com.example.demo.service;

import com.example.demo.dto.PaymentDTO;
import java.util.List;

/**
 * Service interface for managing payment operations. Defines methods for
 * processing and retrieving payments.
 */
public interface PaymentService {

	/**
	 * Processes a payment transaction based on the provided details.
	 * 
	 * @param paymentDTO The payment information submitted by the user.
	 * @return 
	 */
	PaymentDTO processPayment(PaymentDTO paymentDTO);

	/**
	 * Retrieves all processed payments.
	 * 
	 * @return A list of payment DTOs.
	 */
	List<PaymentDTO> getAllPayments();
}
