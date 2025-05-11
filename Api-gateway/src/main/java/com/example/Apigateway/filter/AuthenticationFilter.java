package com.example.Apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.example.Apigateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;
import reactor.core.publisher.Mono;

@Component // Marks this as a Spring-managed component for dependency injection
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator; // Validates if a request should be secured

	@Autowired
	private JwtUtil util; // Utility class for JWT token validation

	public static class Config {
		// Configuration class, can be extended to include additional properties if
		// needed
	}

	public AuthenticationFilter() {
		super(Config.class); // Passes Config class to parent constructor for Gateway Filter
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			// Check if the request needs authentication
			if (validator.isSecured.test(exchange.getRequest())) {
				// Validate if Authorization header exists
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					return handleUnauthorized(exchange.getResponse(), "Missing authorization header");
				}

				// Extract Authorization header value
				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

				// Remove "Bearer " prefix if present
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				try {
					// Extract user role from JWT token
					String role = util.extractRolesFromToken(authHeader);
					String requestedPath = exchange.getRequest().getPath().toString();
					String method = exchange.getRequest().getMethod().name();

					// Check if the role is authorized for the requested path
					if (!isAuthorized(role, requestedPath, method)) {
						return handleUnauthorized(exchange.getResponse(), "Unauthorized access");
					}

				} catch (Exception e) {
					return handleUnauthorized(exchange.getResponse(), "Invalid token");
				}
			}
			return chain.filter(exchange);
		};
	}

	/**
	 * Determines if the request is authorized based on user role and request path.
	 * 
	 * @param role   User role extracted from JWT
	 * @param path   Requested API path
	 * @param method HTTP method (GET, POST, etc.)
	 * @return true if authorized, false otherwise
	 */
	private boolean isAuthorized(String role, String path, String method) {
		if ("ADMIN".equalsIgnoreCase(role)) {
			return path.startsWith("/api/users"); // Admin can access user-related endpoints
		} else if ("TRAVELER".equalsIgnoreCase(role)) {
			return path.startsWith("/api/bookings") || path.startsWith("/api/packages");
		} else if ("HOTEL_MANAGER".equalsIgnoreCase(role)) {
			return path.startsWith("/api/hotels")
					|| (path.startsWith("/api/reviews") && method.equalsIgnoreCase("GET"));
		} else if ("TRAVEL_AGENT".equalsIgnoreCase(role)) {
			return path.startsWith("/api/create-package");
		}
		return false; // Deny access by default
	}

	/**
	 * Handles unauthorized requests by setting HTTP status code to FORBIDDEN.
	 * 
	 * @param response Server HTTP response object
	 * @param message  Custom error message
	 * @return Empty Mono to terminate response flow
	 */
	private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
		response.setStatusCode(HttpStatus.FORBIDDEN);
		return response.setComplete();
	}
}
