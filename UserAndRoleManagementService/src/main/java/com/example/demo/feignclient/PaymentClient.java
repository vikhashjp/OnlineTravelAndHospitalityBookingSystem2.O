package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.example.demo.dto.PaymentDTO;

/**
 * Feign client interface for communication with the Payment Service. Enables
 * declarative REST client functionality to retrieve payment details.
 */
@FeignClient(name = "payment-service") // Defines this interface as a Feign client for the "payment-service"
										// microservice
public interface PaymentClient {

	/**
	 * Fetches payment details for a given user ID from the payment service.
	 * 
	 * @param userId The ID of the user whose payments need to be retrieved.
	 * @return A list of {@code PaymentDTO} containing payment details.
	 */
	@GetMapping("/api/payments/user/{userId}") // Maps this method to the payment service's endpoint
	List<PaymentDTO> getPaymentsByUser(@PathVariable("userId") Long userId);
}
