package com.example.demo.service;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.exceptions.PaymentNotFoundException;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring-managed service component
public class PaymentServiceImpl implements PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class); // Logger for debugging

	@Autowired // Injects the Payment repository to interact with the database
	private PaymentRepository paymentRepository;

	@Autowired // Injects the Invoice service to generate invoices for successful payments
	private InvoiceService invoiceService;

	/**
	 * Processes a payment transaction based on the provided details. If the payment
	 * is successful, an invoice is generated.
	 * 
	 * @param paymentDTO The payment information submitted by the user.
	 */
	@Override
	public void processPayment(PaymentDTO paymentDTO) {
		log.info("Processing payment for Booking ID: {}, User ID: {}, Amount: {}", paymentDTO.getBookingId(),
				paymentDTO.getUserId(), paymentDTO.getAmount());

		// Create new Payment entity
		Payment payment = new Payment();
		payment.setUserId(paymentDTO.getUserId());
		payment.setBookingId(paymentDTO.getBookingId());
		payment.setAmount(paymentDTO.getAmount());
		payment.setStatus(paymentDTO.getStatus());
		payment.setPaymentMethod(paymentDTO.getPaymentMethod());

		// Save payment in the repository
		paymentRepository.save(payment);
		log.info("Payment processed successfully: {}", payment);

		// Generate invoice if payment is successful
		if ("Success".equalsIgnoreCase(paymentDTO.getStatus())) {
			log.info("Payment successful, generating invoice...");
			invoiceService.generateInvoice(paymentDTO.getBookingId(), paymentDTO.getUserId(), paymentDTO.getAmount());
		}
	}

	/**
	 * Retrieves all processed payments. Converts entity objects into DTOs for data
	 * transfer.
	 * 
	 * @return A list of payment DTOs.
	 * @throws PaymentNotFoundException if no payments exist.
	 */
	@Override
	public List<PaymentDTO> getAllPayments() {
		log.info("Fetching all payments...");

		// Convert entity list to DTO list
		List<PaymentDTO> paymentDTOs = paymentRepository.findAll().stream().map(payment -> {
			PaymentDTO dto = new PaymentDTO();
			dto.setPaymentId(payment.getPaymentId());
			dto.setUserId(payment.getUserId());
			dto.setBookingId(payment.getBookingId());
			dto.setAmount(payment.getAmount());
			dto.setStatus(payment.getStatus());
			dto.setPaymentMethod(payment.getPaymentMethod());
			return dto;
		}).collect(Collectors.toList());

		// Validate payment existence
		if (paymentDTOs.isEmpty()) {
			log.error("No payments found.");
			throw new PaymentNotFoundException("No payments found.");
		}

		log.info("Total payments fetched: {}", paymentDTOs.size());
		return paymentDTOs;
	}
}
