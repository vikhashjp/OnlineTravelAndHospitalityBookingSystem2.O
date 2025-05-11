package com.example.demo.service;

import com.example.demo.dto.InvoiceDTO;
import java.util.List;

/**
 * Service interface for managing invoice operations. Defines methods for
 * generating and retrieving invoices.
 */
public interface InvoiceService {

	/**
	 * Generates an invoice for a specific booking and user.
	 * 
	 * @param bookingId   The ID of the booking linked to the invoice.
	 * @param userId      The ID of the user associated with the invoice.
	 * @param totalAmount The total amount billed in the invoice.
	 */
	void generateInvoice(Long bookingId, Long userId, Double totalAmount);

	/**
	 * Retrieves all stored invoices.
	 * 
	 * @return A list of invoice DTOs.
	 */
	List<InvoiceDTO> getAllInvoices();
}
