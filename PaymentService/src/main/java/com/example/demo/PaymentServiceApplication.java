package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Payment Service. This microservice is
 * responsible for handling payment transactions.
 */
@SpringBootApplication // Marks this as a Spring Boot application with auto-configuration enabled
public class PaymentServiceApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
}
