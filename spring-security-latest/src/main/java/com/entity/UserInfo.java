package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for representing user information in the database. Uses JPA
 * annotations for ORM mapping and Lombok for reducing boilerplate code.
 */
@Entity // Marks this class as a JPA entity, mapping it to a database table
@Data // Lombok annotation to generate getter, setter, equals, hashCode, and toString
		// methods
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor // Generates a no-argument constructor for default object creation
public class UserInfo {

	@Id // Defines the primary key for the user entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique user IDs
	private int id; // Unique identifier for the user

	private String name; // Stores the user's name
	private String email; // Stores the user's email address
	private String password; // Stores the user's password (encrypted)
	private String roles; // Stores the user's assigned roles (e.g., ADMIN, USER)

	// Getters and Setters

	/**
	 * Retrieves the unique user ID.
	 * 
	 * @return The user ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique user ID.
	 * 
	 * @param id The ID to be assigned to the user.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the user.
	 * 
	 * @return The user's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name The name to be assigned.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the user's email address.
	 * 
	 * @return The user's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email address.
	 * 
	 * @param email The email to be assigned.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the user's password.
	 * 
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password The password to be assigned.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the user's roles.
	 * 
	 * @return The roles assigned to the user.
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * Sets the user's roles.
	 * 
	 * @param roles The roles to be assigned.
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
}
