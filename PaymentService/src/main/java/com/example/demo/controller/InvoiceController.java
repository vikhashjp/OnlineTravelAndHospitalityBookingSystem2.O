package com.example.demo.controller;

import com.example.demo.dto.InvoiceDTO;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/invoices") // Defines the base path for invoice-related endpoints
@CrossOrigin(origins = "*") // Enables CORS to allow frontend applications to interact with this API
public class InvoiceController {

	@Autowired // Injects the InvoiceService to handle business logic
	private InvoiceService invoiceService;

	/**
	 * Retrieves all invoices from the database.
	 * 
	 * @return A list of invoices or an error message if retrieval fails.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllInvoices() {
		try {
			List<InvoiceDTO> invoices = invoiceService.getAllInvoices();

			// If no invoices exist, return NO CONTENT status
			if (invoices.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No invoices found");
			}

			// Return the list of invoices with OK status
			return ResponseEntity.ok(invoices);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching invoices: " + e.getMessage());
		}
	}
}
