package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "review") // Explicitly defining the table name in the database
public class Review {

	@Id // Defines the primary key for the review entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique review IDs
	private Long id;

	@Column(name = "user_id", nullable = false) // Maps the field to a database column
	@NotNull(message = "User ID is required") // Ensures the user ID is provided
	private Long userId; // ID of the user who submitted the review

	@Column(name = "hotel_id", nullable = false)
	@NotNull(message = "Hotel ID is required") // Validates presence of hotel ID
	private Long hotelId; // ID of the hotel being reviewed

	@Column(name = "rating", nullable = false)
	@Min(value = 1, message = "Rating must be at least 1") // Ensures rating is at least 1
	@Max(value = 5, message = "Rating must not exceed 5") // Restricts rating to a maximum of 5
	private int rating; // Rating given by the user (typically 1 to 5 stars)

	@Column(name = "comment", length = 500)
	@NotBlank(message = "Comment cannot be empty") // Ensures comment is provided
	@Size(max = 500, message = "Comment cannot exceed 500 characters") // Limits comment length
	private String comment; // User's feedback about the hotel

	// Getters and Setters

	/**
	 * Retrieves the unique review ID.
	 * 
	 * @return The review ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique review ID.
	 * 
	 * @param id The review ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the user ID associated with the review.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the review.
	 * 
	 * @param userId The ID of the user who submitted the review.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the hotel ID associated with the review.
	 * 
	 * @return The hotel ID.
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the hotel ID for the review.
	 * 
	 * @param hotelId The ID of the reviewed hotel.
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Retrieves the rating given in the review.
	 * 
	 * @return The rating value.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating for the review.
	 * 
	 * @param rating The user's rating (typically between 1-5 stars).
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Retrieves the comment provided by the user.
	 * 
	 * @return The review comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment for the review.
	 * 
	 * @param comment The user's feedback about the hotel.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
