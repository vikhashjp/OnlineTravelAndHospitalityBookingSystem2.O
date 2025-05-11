package com.example.demo.service;

import com.example.demo.dto.InvoiceDTO;
import com.example.demo.exceptions.InvoiceNotFoundException;
import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring-managed service component
public class InvoiceServiceImpl implements InvoiceService {

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class); // Logger for debugging

	@Autowired // Injects the Invoice repository to interact with the database
	private InvoiceRepository invoiceRepository;

	/**
	 * Generates an invoice for a given booking and user.
	 * 
	 * @param bookingId   The ID of the booking linked to the invoice.
	 * @param userId      The ID of the user associated with the invoice.
	 * @param totalAmount The total amount billed in the invoice.
	 */
	@Override
	public void generateInvoice(Long bookingId, Long userId, Double totalAmount) {
		log.info("Generating invoice for Booking ID: {}, User ID: {}, Total Amount: {}", bookingId, userId,
				totalAmount);

		// Create new invoice entity
		Invoice invoice = new Invoice();
		invoice.setBookingId(bookingId);
		invoice.setUserId(userId);
		invoice.setTotalAmount(totalAmount);
		invoice.setTimestamp(LocalDateTime.now());

		// Save invoice in the repository
		invoiceRepository.save(invoice);
		log.info("Invoice generated successfully: {}", invoice);
	}

	/**
	 * Retrieves all stored invoices from the database. Converts entity objects into
	 * DTOs for data transfer.
	 * 
	 * @return A list of invoice DTOs.
	 * @throws InvoiceNotFoundException if no invoices exist.
	 */
	@Override
	public List<InvoiceDTO> getAllInvoices() {
		log.info("Fetching all invoices...");

		// Convert entity list to DTO list
		List<InvoiceDTO> invoiceDTOs = invoiceRepository.findAll().stream().map(invoice -> {
			InvoiceDTO dto = new InvoiceDTO();
			dto.setInvoiceId(invoice.getInvoiceId());
			dto.setBookingId(invoice.getBookingId());
			dto.setUserId(invoice.getUserId());
			dto.setTotalAmount(invoice.getTotalAmount());
			dto.setTimestamp(invoice.getTimestamp());
			return dto;
		}).collect(Collectors.toList());

		// Validate invoice existence
		if (invoiceDTOs.isEmpty()) {
			log.error("No invoices found.");
			throw new InvoiceNotFoundException("No invoices found.");
		}

		log.info("Total invoices fetched: {}", invoiceDTOs.size());
		return invoiceDTOs;
	}
}
