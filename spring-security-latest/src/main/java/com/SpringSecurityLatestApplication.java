package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Security Latest Application. This
 * serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication // Enables Spring Boot auto-configuration and component scanning
public class SpringSecurityLatestApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLatestApplication.class, args);
	}
}
