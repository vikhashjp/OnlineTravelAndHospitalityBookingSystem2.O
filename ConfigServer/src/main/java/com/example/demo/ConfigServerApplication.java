package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Main application class for the Spring Cloud Config Server. This server
 * provides centralized configuration management for microservices.
 */
@SpringBootApplication // Marks this as a Spring Boot application with auto-configuration enabled
@EnableConfigServer // Enables the Spring Cloud Config Server functionality
public class ConfigServerApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
