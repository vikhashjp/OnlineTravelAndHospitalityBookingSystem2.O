package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Retrieves a user by their email address.
	 * 
	 * @param email The email of the user.
	 * @return A {@code User} object if found, otherwise {@code null}.
	 */
	User findByEmail(String email);
}
