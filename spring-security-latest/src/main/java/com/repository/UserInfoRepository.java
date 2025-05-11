package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserInfo;

import java.util.Optional;

/**
 * Repository interface for managing UserInfo entities in the database. Extends
 * {@link JpaRepository} to provide built-in CRUD operations.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	/**
	 * Retrieves a user by their username.
	 * 
	 * @param username The username of the user.
	 * @return An {@code Optional<UserInfo>} containing the user details if found.
	 */
	Optional<UserInfo> findByName(String username);
}
