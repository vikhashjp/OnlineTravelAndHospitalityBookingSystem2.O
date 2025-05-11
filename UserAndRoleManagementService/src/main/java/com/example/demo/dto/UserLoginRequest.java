package com.example.demo.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) for handling user login requests. Used to transfer
 * login credentials securely between client and server.
 */
@Data // Lombok annotation to generate getter, setter, equals, hashCode, and toString
		// methods
public class UserLoginRequest {

	private String email; // Stores the user's email for authentication
	private String password; // Stores the user's password for authentication
}
