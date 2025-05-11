package com.example.Apigateway.filter;

import java.util.function.Predicate; // Functional interface for defining conditions
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component // Marks this class as a Spring-managed component for dependency injection
public class RouteValidator {

	/**
	 * List of open API endpoints that do not require authentication. Requests to
	 * these paths will be allowed without authorization headers.
	 */
	public static final String[] OPEN_API_ENDPOINTS = { "/api/users/register", // User registration
			"/auth/register", // Authentication register endpoint
			"/auth/new", // New authentication flow
			"/auth/validate", // Validate authentication token
			"/eureka" // Eureka service registry (should remain open)
	};

	/**
	 * Predicate function to determine if a request requires authentication. If the
	 * request URL matches an endpoint in OPEN_API_ENDPOINTS, authentication is
	 * bypassed.
	 */
	public Predicate<ServerHttpRequest> isSecured = request -> {
		String path = request.getPath().toString();
		for (String endpoint : OPEN_API_ENDPOINTS) {
			if (path.contains(endpoint)) {
				return false; // This endpoint is publicly accessible
			}
		}
		return true; // This endpoint requires authentication
	};
}
