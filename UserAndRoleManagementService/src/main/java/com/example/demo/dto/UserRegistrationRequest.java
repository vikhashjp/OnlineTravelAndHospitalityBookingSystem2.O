package com.example.demo.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) for handling user registration requests. Used to
 * transfer new user details securely between client and server.
 */
@Data // Lombok annotation to automatically generate getter, setter, equals, hashCode,
		// and toString methods
public class UserRegistrationRequest {

	private String name; // Stores the user's name
	private String email; // Stores the user's email address
	private String password; // Stores the user's password for authentication
	private String role; // Stores the role assigned to the user (e.g., ADMIN, USER)
	private String contactNumber; // Stores the user's contact number
}
