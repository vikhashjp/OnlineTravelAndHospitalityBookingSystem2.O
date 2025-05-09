package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentServiceImpl;
import com.example.demo.service.InvoiceService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceApplicationTests {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private InvoiceService invoiceService; // Added Invoice Service Mock

    @InjectMocks
    private PaymentServiceImpl paymentService; // Changed to Impl for correctness

    @Test
    void testProcessPayment() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setUserId(101L);
        paymentDTO.setBookingId(202L);
        paymentDTO.setAmount(500.0);
        paymentDTO.setStatus("Success");
        paymentDTO.setPaymentMethod("Credit Card");

        Payment paymentEntity = new Payment();
        paymentEntity.setPaymentId(1L);
        paymentEntity.setUserId(paymentDTO.getUserId());
        paymentEntity.setBookingId(paymentDTO.getBookingId());
        paymentEntity.setAmount(paymentDTO.getAmount());
        paymentEntity.setStatus(paymentDTO.getStatus());
        paymentEntity.setPaymentMethod(paymentDTO.getPaymentMethod());

        when(paymentRepository.save(any(Payment.class))).thenReturn(paymentEntity);

        paymentService.processPayment(paymentDTO);

        verify(paymentRepository, times(1)).save(any(Payment.class));

        // Verify Invoice generation for successful payments
        verify(invoiceService, times(1)).generateInvoice(paymentDTO.getBookingId(), paymentDTO.getUserId(), paymentDTO.getAmount());
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = new Payment();
        payment1.setPaymentId(1L);
        payment1.setUserId(101L);
        payment1.setBookingId(202L);
        payment1.setAmount(500.0);
        payment1.setStatus("Success");
        payment1.setPaymentMethod("Credit Card");

        Payment payment2 = new Payment();
        payment2.setPaymentId(2L);
        payment2.setUserId(102L);
        payment2.setBookingId(205L);
        payment2.setAmount(800.0);
        payment2.setStatus("Failed");
        payment2.setPaymentMethod("PayPal");

        List<Payment> payments = Arrays.asList(payment1, payment2);

        when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentDTO> paymentDTOs = paymentService.getAllPayments();

        assertNotNull(paymentDTOs);
        assertEquals(2, paymentDTOs.size());
        assertEquals("Success", paymentDTOs.get(0).getStatus());

        verify(paymentRepository, times(1)).findAll();
    }
}
