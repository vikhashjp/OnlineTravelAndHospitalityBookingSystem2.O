package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) for managing user reviews. Used to transfer
 * review-related data between layers of the application.
 */
public class ReviewDTO {

	private Long id; // Unique identifier for the review
	private Long userId; // ID of the user who submitted the review
	private Long hotelId; // ID of the hotel being reviewed
	private int rating; // Rating given by the user (typically 1 to 5 stars)
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
	 * @param userId The user ID who submitted the review.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the hotel ID that the review is associated with.
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
