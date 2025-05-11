package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main application class for the User and Role Management Service. This serves
 * as the entry point for the Spring Boot application.
 */
@SpringBootApplication // Enables Spring Boot auto-configuration and component scanning
@EnableDiscoveryClient // Registers this service as a Eureka client for service discovery
@EnableFeignClients // Enables Feign clients for RESTful communication with other microservices
public class UserAndRoleManagementServiceApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserAndRoleManagementServiceApplication.class, args);
	}
}
