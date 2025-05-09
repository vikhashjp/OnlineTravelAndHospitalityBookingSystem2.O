package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.example.demo.dto.PaymentDTO;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/api/payments/user/{userId}")
    List<PaymentDTO> getPaymentsByUser(@PathVariable("userId") Long userId);
}
