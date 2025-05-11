package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

/**
 * DTO (Data Transfer Object) for user login responses. Used to return
 * authentication status and user details to the client.
 */
@Data // Lombok annotation to automatically generate getter, setter, equals, hashCode,
		// and toString methods
public class UserLoginResponse {

	private String message; // Stores the response message indicating login success or failure
	private User user; // Stores user details upon successful authentication

	/**
	 * Constructs a new `UserLoginResponse` with the provided message and user
	 * details.
	 * 
	 * @param message The status message (e.g., "Login successful!" or "Invalid
	 *                credentials").
	 * @param user    The authenticated user details.
	 */
	public UserLoginResponse(String message, User user) {
		this.message = message;
		this.user = user;
	}
}
