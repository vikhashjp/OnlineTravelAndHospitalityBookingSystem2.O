package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the User and Role Management Service using JUnit and Mockito.
 * Ensures correctness of user registration, retrieval, and role updates.
 */
@SpringBootTest // Marks this test class as a Spring Boot test
class UserAndRoleManagementServiceApplicationTests {

	@Mock // Creates a mock instance of UserRepository
	private UserRepository userRepository;

	@InjectMocks // Injects the mocked repository into the service
	private UserServiceImpl userService;

	/**
	 * Tests user registration functionality. Ensures that the user is successfully
	 * saved and returned.
	 */
	@Test
	void testRegisterUser() {
		User user = new User();
		user.setName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setPassword("password123");
		user.setRole("Traveler");
		user.setContactNumber("9876543210");

		when(userRepository.save(any(User.class))).thenReturn(user);

		User registeredUser = userService.registerUser(user);

		assertNotNull(registeredUser);
		assertEquals("John Doe", registeredUser.getName());
		assertEquals("Traveler", registeredUser.getRole());

		verify(userRepository, times(1)).save(any(User.class));
	}

	/**
	 * Tests retrieval of a user by email when the user exists. Ensures that the
	 * correct user is returned.
	 */
	@Test
	void testGetUserByEmail_Success() {
		User user = new User();
		user.setUserId(1L);
		user.setEmail("john.doe@example.com");
		user.setName("John Doe");

		when(userRepository.findByEmail("john.doe@example.com")).thenReturn(user);

		User foundUser = userService.getUserByEmail("john.doe@example.com");

		assertNotNull(foundUser);
		assertEquals("John Doe", foundUser.getName());

		verify(userRepository, times(1)).findByEmail("john.doe@example.com");
	}

	/**
	 * Tests retrieval of a user by email when the user does not exist. Ensures that
	 * a UserNotFoundException is thrown.
	 */
	@Test
	void testGetUserByEmail_NotFound() {
		when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

		assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("unknown@example.com"));

		verify(userRepository, times(1)).findByEmail("unknown@example.com");
	}

	/**
	 * Tests updating a user's role successfully. Ensures that the role is updated
	 * and saved.
	 */
	@Test
	void testUpdateUserRole_Success() {
		User user = new User();
		user.setUserId(1L);
		user.setRole("Traveler");

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);

		User updatedUser = userService.updateUserRole(1L, "Admin");

		assertNotNull(updatedUser);
		assertEquals("Admin", updatedUser.getRole());

		verify(userRepository, times(1)).findById(1L);
		verify(userRepository, times(1)).save(any(User.class));
	}

	/**
	 * Tests updating a user's role when the user does not exist. Ensures that a
	 * UserNotFoundException is thrown.
	 */
	@Test
	void testUpdateUserRole_NotFound() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> userService.updateUserRole(1L, "Admin"));

		verify(userRepository, times(1)).findById(1L);
		verify(userRepository, never()).save(any(User.class));
	}
}
