package com.example.demo.service;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for user-related operations. Provides user
 * registration, retrieval, role updates, and listing functionality.
 */
@Service // Marks this class as a Spring-managed service component
public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class); // Logger for debugging and
																						// tracking execution

	@Autowired // Injects the UserRepository for database operations
	private UserRepository userRepository;

	/**
	 * Registers a new user in the system.
	 * 
	 * @param user The user to be registered.
	 * @return The registered user object.
	 */
	@Override
	public User registerUser(User user) {
		log.info("Registering new user: {}", user.getEmail());
		User savedUser = userRepository.save(user);
		log.info("User registered successfully: {}", savedUser);
		return savedUser;
	}

	/**
	 * Retrieves user details by email. Throws {@code UserNotFoundException} if the
	 * user is not found.
	 * 
	 * @param email The email of the user to retrieve.
	 * @return The found user object.
	 */
	@Override
	public User getUserByEmail(String email) {
		log.info("Fetching user by email: {}", email);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			log.error("User not found with email: {}", email);
			throw new UserNotFoundException("User not found with email: " + email);
		}
		log.info("User retrieved successfully: {}", user);
		return user;
	}

	/**
	 * Updates the role of an existing user. Throws {@code UserNotFoundException} if
	 * the user does not exist.
	 * 
	 * @param userId The ID of the user whose role is being updated.
	 * @param role   The new role assigned to the user.
	 * @return The updated user object.
	 */
	@Override
	public User updateUserRole(Long userId, String role) {
		log.info("Updating role for User ID: {} to {}", userId, role);
		User user = userRepository.findById(userId).orElseThrow(() -> {
			log.error("User not found with ID: {}", userId);
			return new UserNotFoundException("User not found with ID: " + userId);
		});
		user.setRole(role);
		User updatedUser = userRepository.save(user);
		log.info("User role updated successfully: {}", updatedUser);
		return updatedUser;
	}

	/**
	 * Retrieves a list of all registered users.
	 * 
	 * @return A list of user objects.
	 */
	@Override
	public List<User> getAllUsers() {
		log.info("Fetching all users...");
		List<User> users = userRepository.findAll();
		log.info("Total users retrieved: {}", users.size());
		return users;
	}
}
