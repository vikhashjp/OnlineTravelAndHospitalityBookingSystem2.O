package com.example.demo.service;

import com.example.demo.dto.InvoiceDTO;
import java.util.List;

public interface InvoiceService {
    void generateInvoice(Long bookingId, Long userId, Double totalAmount);
    List<InvoiceDTO> getAllInvoices();
}
