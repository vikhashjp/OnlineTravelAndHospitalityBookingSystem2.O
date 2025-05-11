package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.UserInfo;
import com.repository.UserInfoRepository;

/**
 * Service class for managing user operations such as registration and role
 * retrieval.
 */
@Service // Marks this class as a Spring-managed service component
public class UserService {

	@Autowired // Injects the UserInfoRepository for database operations
	private UserInfoRepository repository;

	@Autowired // Injects the PasswordEncoder for secure password hashing
	private PasswordEncoder passwordEncoder;

	/**
	 * Registers a new user in the system. Ensures the username is unique before
	 * saving the user.
	 * 
	 * @param userInfo The user details provided for registration.
	 * @return A success or failure message based on registration status.
	 */
	public String addUser(UserInfo userInfo) {
		String name = userInfo.getName();
		UserInfo existingUser = repository.findByName(name).orElse(null);
		System.out.println(existingUser); // Debugging statement (can be removed in production)

		if (existingUser == null) {
			// Encode password before storing
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			repository.save(userInfo);
			return "Registration Successful!";
		} else {
			return "This Username is Already Registered.";
		}
	}

	/**
	 * Retrieves the roles assigned to a user.
	 * 
	 * @param username The username whose roles are requested.
	 * @return The roles associated with the user or "Not Found" if the user does
	 *         not exist.
	 */
	public String getRoles(String username) {
		UserInfo user = repository.findByName(username).orElse(null);
		if (user != null) {
			return user.getRoles();
		}
		return "Not Found";
	}
}
