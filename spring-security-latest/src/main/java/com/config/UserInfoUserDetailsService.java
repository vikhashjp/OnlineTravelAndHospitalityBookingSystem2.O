package com.config;

import com.entity.UserInfo;
import com.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // Marks this class as a Spring-managed component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired // Injects the UserInfoRepository to fetch user details from the database
	private UserInfoRepository repository;

	/**
	 * Loads user details by username for authentication.
	 * 
	 * @param username The username used for authentication.
	 * @return {@code UserDetails} containing user information.
	 * @throws UsernameNotFoundException if the user is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username); // Fetch user details by name

		// Convert UserInfo entity to UserDetails or throw exception if user is not
		// found
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}
}
