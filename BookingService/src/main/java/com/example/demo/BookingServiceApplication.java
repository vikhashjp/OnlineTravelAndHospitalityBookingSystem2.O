package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this class as the main entry point for the Spring Boot application
@EnableFeignClients // Enables Feign clients for making HTTP requests to other microservices
public class BookingServiceApplication {

	public static void main(String[] args) {
		// Bootstraps and starts the Spring Boot application
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
