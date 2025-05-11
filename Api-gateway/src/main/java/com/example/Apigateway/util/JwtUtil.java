package com.example.Apigateway.util;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service // Marks this class as a Spring-managed service component
public class JwtUtil {

	/**
	 * Secret key used for JWT signature validation. Ensure this key is stored
	 * securely (e.g., environment variables) instead of hardcoding.
	 */
	public static final String SECRET = "d3780ec3d1cfaba271e0538d4fae686d8367e10155ee424691fbf191eabec53d";

	/**
	 * Validates the JWT token by parsing its claims.
	 * 
	 * @param token The JWT token to validate. If parsing fails, an exception is
	 *              thrown indicating an invalid token.
	 */
	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	/**
	 * Extracts roles from the JWT token.
	 * 
	 * @param token The JWT token containing role claims.
	 * @return The user role stored in the JWT token. Assumes roles are stored under
	 *         the "roles" claim in the token.
	 */
	public String extractRolesFromToken(final String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
		System.out.println("Step2" + claims);

		// Extract role information from JWT claims
		String authorities = (String) claims.get("roles");
		System.out.println("Step3" + authorities);

		return authorities;
	}

	/**
	 * Generates a signing key from the provided secret.
	 * 
	 * @return The HMAC SHA key used for signing JWTs.
	 */
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
