package com.example.demo.controller;

import com.example.demo.dto.InvoiceDTO;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*") // Enables CORS for frontend interaction
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<Object> getAllInvoices() {
        try {
            List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
            if (invoices.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No invoices found");
            }
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching invoices: " + e.getMessage());
        }
    }
}
