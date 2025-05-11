package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "support_ticket") // Explicitly defining the table name in the database
public class SupportTicket {

	@Id // Defines the primary key for the support ticket entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique support ticket IDs
	private Long id;

	@Column(name = "user_id", nullable = false) // Maps the field to a database column
	@NotNull(message = "User ID is required") // Ensures the user ID is provided
	private Long userId; // ID of the user who submitted the support ticket

	@Column(name = "issue", nullable = false, length = 500)
	@NotBlank(message = "Issue description cannot be empty") // Ensures issue description is provided
	@Size(max = 500, message = "Issue description cannot exceed 500 characters") // Limits issue description length
	private String issue; // Description of the issue or complaint

	@Column(name = "status", nullable = false)
	@NotBlank(message = "Status is required") // Ensures status is provided
	@Pattern(regexp = "Open|In Progress|Resolved|Closed", message = "Invalid status value") // Restricts status values
																							// to predefined options
	private String status; // Current status of the support ticket

	@Column(name = "assigned_agent")
	@Size(max = 100, message = "Assigned Agent name cannot exceed 100 characters") // Limits assigned agent name length
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
	 * @param status The current status (Open, In Progress, Resolved, Closed).
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
