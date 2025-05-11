package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationRequest;
import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserLoginResponse;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing user operations such as registration, login, and role
 * updates. Handles authentication and user retrieval requests.
 */
@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/users") // Defines the base path for user-related operations
public class UserController {

	@Autowired // Injects the IUserService to manage user-related operations
	private IUserService userService;

	/**
	 * Registers a new user in the system.
	 * 
	 * @param request The registration request containing user details.
	 * @return A ResponseEntity containing the registered user details.
	 */
	@PostMapping("/register") // Endpoint: http://localhost:9090/api/users/register
	public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());
		user.setContactNumber(request.getContactNumber());

		User registeredUser = userService.registerUser(user);
		return ResponseEntity.ok(registeredUser);
	}

	/**
	 * Authenticates a user login request. Validates email and password and returns
	 * a login response.
	 * 
	 * @param request The login request containing user credentials.
	 * @return A ResponseEntity with authentication success or failure.
	 */
	@PostMapping("/login") // Endpoint: http://localhost:9090/api/users/login
	public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest request) {
		User user = userService.getUserByEmail(request.getEmail());

		if (user == null || !user.getPassword().equals(request.getPassword())) {
			return ResponseEntity.status(401).body(new UserLoginResponse("Invalid credentials", null));
		}

		return ResponseEntity.ok(new UserLoginResponse("Login successful!", user));
	}

	/**
	 * Retrieves user details by email.
	 * 
	 * @param email The email of the requested user.
	 * @return A ResponseEntity containing the user details.
	 */
	@GetMapping("/{email}") // Endpoint: http://localhost:9090/api/users/{email}
	public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email) {
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}

	/**
	 * Updates the role of a user.
	 * 
	 * @param userId The ID of the user whose role is being updated.
	 * @param role   The new role assigned to the user.
	 * @return A ResponseEntity containing the updated user details.
	 */
	@PatchMapping("/{userId}/role") // Endpoint: http://localhost:9090/api/users/{userId}/role
	public ResponseEntity<User> updateUserRole(@PathVariable Long userId, @RequestParam String role) {
		System.out.println(userId + "  " + role + " in user controller"); // Debugging statement (remove in production)
		User updatedUser = userService.updateUserRole(userId, role);
		return ResponseEntity.ok(updatedUser);
	}
}
