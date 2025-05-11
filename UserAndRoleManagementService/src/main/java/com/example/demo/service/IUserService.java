package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

/**
 * Service interface for managing user-related operations. Defines methods for
 * user registration, retrieval, role updates, and listing users.
 */
public interface IUserService {

	/**
	 * Registers a new user in the system.
	 * 
	 * @param user The user to be registered.
	 * @return The registered user object.
	 */
	User registerUser(User user);

	/**
	 * Retrieves user details by email.
	 * 
	 * @param email The email of the user to retrieve.
	 * @return A {@code User} object if found, otherwise {@code null}.
	 */
	User getUserByEmail(String email);

	/**
	 * Updates the role of an existing user.
	 * 
	 * @param userId The ID of the user whose role is being updated.
	 * @param role   The new role assigned to the user.
	 * @return The updated {@code User} object.
	 */
	User updateUserRole(Long userId, String role);

	/**
	 * Retrieves a list of all registered users.
	 * 
	 * @return A list of {@code User} objects.
	 */
	List<User> getAllUsers(); // Additional method if needed
}
