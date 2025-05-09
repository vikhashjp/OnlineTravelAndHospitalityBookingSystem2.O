package com.example.demo.service;

import com.example.demo.dto.InvoiceDTO;
import com.example.demo.model.Invoice;
import com.example.demo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void generateInvoice(Long bookingId, Long userId, Double totalAmount) {
        Invoice invoice = new Invoice();
        invoice.setBookingId(bookingId);
        invoice.setUserId(userId);
        invoice.setTotalAmount(totalAmount);
        invoice.setTimestamp(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoice -> {
            InvoiceDTO dto = new InvoiceDTO();
            dto.setInvoiceId(invoice.getInvoiceId());
            dto.setBookingId(invoice.getBookingId());
            dto.setUserId(invoice.getUserId());
            dto.setTotalAmount(invoice.getTotalAmount());
            dto.setTimestamp(invoice.getTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }
}
