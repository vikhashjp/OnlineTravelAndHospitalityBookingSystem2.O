package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main application class for the Eureka Server. Eureka Server is used for
 * service discovery in a microservices architecture.
 */
@SpringBootApplication // Marks this as a Spring Boot application with auto-configuration enabled
@EnableEurekaServer // Enables Netflix Eureka Server for service registry and discovery
public class EurekaServerApplication {

	/**
	 * Main method to start the Spring Boot Eureka Server application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
