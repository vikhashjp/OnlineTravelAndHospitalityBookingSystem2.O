package com.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.config.UserInfoUserDetailsService;
import com.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication Filter that intercepts requests to validate JWT tokens.
 * Ensures that authenticated users are properly identified and authorized.
 */
@Component // Marks this class as a Spring-managed component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired // Injects JwtService for token validation and extraction
	private JwtService jwtService;

	@Autowired // Injects UserDetailsService to retrieve user details
	private UserInfoUserDetailsService userDetailsService;

	/**
	 * Filters incoming requests to validate JWT tokens and authenticate users.
	 * 
	 * @param request     Incoming HTTP request.
	 * @param response    HTTP response to be sent.
	 * @param filterChain Filter chain to pass the request forward.
	 * @throws ServletException If an error occurs during request filtering.
	 * @throws IOException      If an I/O error occurs during filtering.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Extract JWT token from Authorization header
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}

		// Validate token and set authentication in SecurityContextHolder
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		// Continue the filter chain
		filterChain.doFilter(request, response);
	}
}
