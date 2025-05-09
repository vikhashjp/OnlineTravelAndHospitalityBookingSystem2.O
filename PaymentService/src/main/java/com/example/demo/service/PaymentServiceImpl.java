package com.example.demo.service;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void processPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setUserId(paymentDTO.getUserId());
        payment.setBookingId(paymentDTO.getBookingId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        paymentRepository.save(payment);

        if ("Success".equalsIgnoreCase(paymentDTO.getStatus())) {
            invoiceService.generateInvoice(paymentDTO.getBookingId(), paymentDTO.getUserId(), paymentDTO.getAmount());
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            System.out.println("Payments retrieved: " + payments.size());

            return payments.stream().map(payment -> {
                PaymentDTO dto = new PaymentDTO();
                dto.setPaymentId(payment.getPaymentId());
                dto.setUserId(payment.getUserId());
                dto.setBookingId(payment.getBookingId());
                dto.setAmount(payment.getAmount());
                dto.setStatus(payment.getStatus());
                dto.setPaymentMethod(payment.getPaymentMethod());
                return dto;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving payments: " + e.getMessage());
            throw new RuntimeException("Unable to fetch payments!", e);
        }
    }


}
