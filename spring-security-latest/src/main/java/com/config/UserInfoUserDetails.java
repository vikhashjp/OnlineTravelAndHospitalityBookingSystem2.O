package com.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entity.UserInfo;

/**
 * Custom implementation of {@link UserDetails} to store user authentication
 * details. Extracts user information from {@code UserInfo} and maps roles to
 * Spring Security authorities.
 */
public class UserInfoUserDetails implements UserDetails {

	private String name; // Stores the username
	private String password; // Stores the encrypted password
	private List<GrantedAuthority> authorities; // Stores user roles as granted authorities

	/**
	 * Constructor that initializes user details from {@code UserInfo} entity.
	 * 
	 * @param userInfo The user entity containing authentication details.
	 */
	public UserInfoUserDetails(UserInfo userInfo) {
		name = userInfo.getName();
		password = userInfo.getPassword();
		authorities = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new) // Maps roles to
																										// authorities
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the granted authorities (roles) assigned to the user.
	 * 
	 * @return A collection of granted authorities.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Retrieves the user's encrypted password.
	 * 
	 * @return The encrypted password.
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Retrieves the username for authentication.
	 * 
	 * @return The username.
	 */
	@Override
	public String getUsername() {
		return name;
	}

	/**
	 * Checks whether the account is not expired. Always returns {@code true} (No
	 * expiration logic applied).
	 * 
	 * @return {@code true} if the account is non-expired.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Checks whether the account is not locked. Always returns {@code true} (No
	 * locking logic applied).
	 * 
	 * @return {@code true} if the account is non-locked.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Checks whether the credentials are not expired. Always returns {@code true}
	 * (No expiration logic applied).
	 * 
	 * @return {@code true} if credentials are non-expired.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Checks whether the account is enabled. Always returns {@code true} (No
	 * disabling logic applied).
	 * 
	 * @return {@code true} if the account is enabled.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
