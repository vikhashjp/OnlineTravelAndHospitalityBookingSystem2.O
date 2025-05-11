package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Package and Itinerary Service. This
 * microservice manages travel packages and itinerary-related functionalities.
 */
@SpringBootApplication // Marks this as a Spring Boot application with auto-configuration enabled
public class PackageAndItineraryServiceApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PackageAndItineraryServiceApplication.class, args);
	}
}
