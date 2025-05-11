package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.dto.AuthRequest;
import com.entity.UserInfo;
import com.repository.UserInfoRepository;
import com.service.JwtService;
import com.service.UserService;

/**
 * Controller for handling authentication and user-related requests.
 */
@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/auth") // Defines the base path for authentication endpoints
@CrossOrigin("*") // Allows cross-origin requests from all sources
public class AuthController {

	@Autowired // Injects the UserService to manage user-related operations
	private UserService service;

	@Autowired // Injects the JwtService to handle JWT token generation
	private JwtService jwtService;

	@Autowired // Injects the UserInfoRepository for user retrieval
	private UserInfoRepository repo;

	@Autowired // Injects the AuthenticationManager for user authentication
	private AuthenticationManager authenticationManager;

	/**
	 * Returns a welcome message for unsecured endpoints.
	 * 
	 * @return A simple welcome message.
	 */
	@GetMapping("/welcome") // Endpoint: http://localhost:9090/auth/welcome
	public String welcome() {
		return "Welcome, this endpoint is not secure.";
	}

	/**
	 * Adds a new user to the system.
	 * 
	 * @param userInfo The user details to be stored.
	 * @return Success message indicating user creation.
	 */
	@PostMapping("/new") // Endpoint: http://localhost:9090/auth/new
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	/**
	 * Authenticates a user and generates a JWT token upon successful
	 * authentication.
	 * 
	 * @param authRequest Contains username and password for authentication.
	 * @return A JWT token if authentication is successful.
	 * @throws UsernameNotFoundException if authentication fails.
	 */
	@PostMapping("/authenticate") // Endpoint: http://localhost:9090/auth/authenticate
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			UserInfo user = repo.findByName(authRequest.getUsername()).orElse(null);
			return jwtService.generateToken(authRequest.getUsername(), user.getRoles());
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

	/**
	 * Retrieves the roles assigned to a user.
	 * 
	 * @param username The username whose roles are requested.
	 * @return The roles associated with the user.
	 */
	@GetMapping("/getroles/{username}") // Endpoint: http://localhost:9090/auth/getroles/{username}
	public String getRoles(@PathVariable String username) {
		return service.getRoles(username);
	}
}
