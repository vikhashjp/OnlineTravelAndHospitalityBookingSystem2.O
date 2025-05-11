package com.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for managing JWT token operations, including generation,
 * validation, and extraction of claims.
 */
@Component // Marks this class as a Spring-managed component
public class JwtService {

	// Secret key used for signing JWTs (should be securely stored)
	public static final String SECRET = "d3780ec3d1cfaba271e0538d4fae686d8367e10155ee424691fbf191eabec53d";

	/**
	 * Extracts the username from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The extracted username.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts the expiration date from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The expiration date.
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extracts a specific claim from the JWT token.
	 * 
	 * @param token          The JWT token.
	 * @param claimsResolver Function to resolve claims.
	 * @param <T>            Type of claim to extract.
	 * @return Extracted claim.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extracts all claims from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The claims.
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	/**
	 * Checks if the token has expired.
	 * 
	 * @param token The JWT token.
	 * @return {@code true} if expired, {@code false} otherwise.
	 */
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Validates the token against user details. Ensures the username matches and
	 * the token is not expired.
	 * 
	 * @param token       The JWT token.
	 * @param userDetails The user details for verification.
	 * @return {@code true} if valid, {@code false} otherwise.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	/**
	 * Generates a JWT token containing user roles.
	 * 
	 * @param userName The username to be included.
	 * @param roles    The user roles.
	 * @return A JWT token.
	 */
	public String generateToken(String userName, String roles) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", roles);
		System.out.println(claims);
		return createToken(claims, userName);
	}

	/**
	 * Creates a JWT token with claims and expiration settings.
	 * 
	 * @param claims   The claims to include.
	 * @param userName The username to be stored.
	 * @return A JWT token.
	 */
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token expires in 1 hour
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	/**
	 * Retrieves the signing key used for JWT encryption.
	 * 
	 * @return The signing key.
	 */
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
