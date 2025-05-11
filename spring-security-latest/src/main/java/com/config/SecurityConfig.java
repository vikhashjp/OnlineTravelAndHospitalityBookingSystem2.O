package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JwtAuthFilter;

@Configuration // Marks this class as a Spring security configuration
@EnableWebSecurity // Enables Spring Security for this application
@EnableMethodSecurity // Allows method-level security annotations like @PreAuthorize
public class SecurityConfig {

	@Autowired // Injects the JWT authentication filter
	private JwtAuthFilter authFilter;

	/**
	 * Defines the UserDetailsService bean to handle user authentication details.
	 * 
	 * @return An instance of {@code UserInfoUserDetailsService}.
	 */
	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}

	/**
	 * Configures security settings for HTTP requests. Disables CSRF protection,
	 * defines authentication rules, session policies, and applies filters.
	 * 
	 * @param http The HTTP security configuration.
	 * @return The configured {@code SecurityFilterChain}.
	 * @throws Exception In case of configuration errors.
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) // Disables CSRF protection for stateless authentication
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/auth/authenticate", "/auth/getroles/**", "/auth/**").permitAll()) // Allows
																												// authentication
																												// routes
																												// without
																												// requiring
																												// authentication
				.authorizeHttpRequests(requests -> requests.requestMatchers("/api/bookings", "/api/packages",
						"/api/flights", "/api/hotels", "/api/itineraries", "/api/invoices", "/api/payments",
						"/api/review-support", "/api/users").authenticated()) // Requires authentication for API routes
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configures
																													// stateless
																													// session
																													// management
																													// for
																													// JWT
																													// authentication
				.authenticationProvider(authenticationProvider()) // Sets the authentication provider
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // Adds JWT authentication
																							// filter before
																							// username-password
																							// authentication
				.build();
	}

	/**
	 * Defines the PasswordEncoder bean for hashing passwords.
	 * 
	 * @return An instance of {@code BCryptPasswordEncoder}.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures the authentication provider using DAO authentication with password
	 * encoding.
	 * 
	 * @return A {@code DaoAuthenticationProvider} instance.
	 */
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService()); // Uses custom UserDetailsService
		authenticationProvider.setPasswordEncoder(passwordEncoder()); // Enables password hashing
		return authenticationProvider;
	}

	/**
	 * Configures the authentication manager to manage authentication processes.
	 * 
	 * @param config The authentication configuration.
	 * @return An instance of {@code AuthenticationManager}.
	 * @throws Exception In case of configuration errors.
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
