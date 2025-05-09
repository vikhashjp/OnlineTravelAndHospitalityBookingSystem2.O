package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "support_ticket")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User ID is required")
    private Long userId;

    @Column(name = "issue", nullable = false, length = 500)
    @NotBlank(message = "Issue description cannot be empty")
    @Size(max = 500, message = "Issue description cannot exceed 500 characters")
    private String issue;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Open|In Progress|Resolved|Closed", message = "Invalid status value")
    private String status;

    @Column(name = "assigned_agent")
    @Size(max = 100, message = "Assigned Agent name cannot exceed 100 characters")
    private String assignedAgent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedAgent() {
		return assignedAgent;
	}

	public void setAssignedAgent(String assignedAgent) {
		this.assignedAgent = assignedAgent;
	}

    // Getters and Setters
    
}
