package com.example.demo.dto;

public class SupportTicketDTO {
    private Long id;
    private Long userId;
    private String issue;
    private String status;
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

    
}
