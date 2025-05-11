package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for authentication requests. Used to transfer user
 * login credentials during authentication.
 */
@Data // Lombok annotation to generate getter, setter, equals, hashCode, and toString
		// methods
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor // Generates a no-argument constructor for default object creation
public class AuthRequest {

	private String username; // Stores the username for authentication
	private String password; // Stores the password for authentication

	// Getters and Setters

	/**
	 * Retrieves the username from the authentication request.
	 * 
	 * @return The username entered by the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username for the authentication request.
	 * 
	 * @param username The username to be stored.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves the password from the authentication request.
	 * 
	 * @return The password entered by the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password for the authentication request.
	 * 
	 * @param password The password to be stored.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
