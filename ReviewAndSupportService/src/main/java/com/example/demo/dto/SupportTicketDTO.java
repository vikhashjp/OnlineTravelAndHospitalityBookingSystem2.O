package com.example.demo.dto;

/**
 * DTO (Data Transfer Object) for managing support ticket details. Used to
 * transfer support request-related data between layers of the application.
 */
public class SupportTicketDTO {

	private Long id; // Unique identifier for the support ticket
	private Long userId; // ID of the user who submitted the ticket
	private String issue; // Description of the issue or complaint
	private String status; // Current status of the ticket (e.g., Open, In Progress, Resolved)
	private String assignedAgent; // Name or ID of the support agent handling the ticket

	// Getters and Setters

	/**
	 * Retrieves the unique support ticket ID.
	 * 
	 * @return The ticket ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique support ticket ID.
	 * 
	 * @param id The ticket ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the user ID associated with the support ticket.
	 * 
	 * @return The user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID for the support ticket.
	 * 
	 * @param userId The ID of the user who submitted the ticket.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the issue description.
	 * 
	 * @return The issue reported in the support ticket.
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * Sets the issue description for the support ticket.
	 * 
	 * @param issue The user's complaint or request.
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * Retrieves the current status of the support ticket.
	 * 
	 * @return The ticket status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the support ticket.
	 * 
	 * @param status The current status (Open, In Progress, Resolved).
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the assigned support agent.
	 * 
	 * @return The name or ID of the assigned agent.
	 */
	public String getAssignedAgent() {
		return assignedAgent;
	}

	/**
	 * Sets the assigned support agent.
	 * 
	 * @param assignedAgent The agent responsible for resolving the ticket.
	 */
	public void setAssignedAgent(String assignedAgent) {
		this.assignedAgent = assignedAgent;
	}
}
