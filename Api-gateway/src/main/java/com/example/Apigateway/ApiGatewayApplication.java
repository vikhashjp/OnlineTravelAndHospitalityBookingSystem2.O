package com.example.Apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin; // Enables Cross-Origin Resource Sharing (CORS) for frontend access
import org.springframework.web.reactive.config.EnableWebFlux; // Enables WebFlux, allowing reactive programming capabilities

@SpringBootApplication // Marks this as a Spring Boot application and handles component scanning
public class ApiGatewayApplication {

	public static void main(String[] args) {
		// Bootstraps and launches the Spring Boot application
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
