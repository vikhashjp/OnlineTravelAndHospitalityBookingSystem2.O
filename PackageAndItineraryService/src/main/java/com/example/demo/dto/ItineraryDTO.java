package com.example.demo.dto;

public class ItineraryDTO {
    private Long id;
    private Long userId;
    private Long packageId;
    private String customizationDetails;

    // Getters and Setters
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
    public Long getPackageId() {
        return packageId;
    }
    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
    public String getCustomizationDetails() {
        return customizationDetails;
    }
    public void setCustomizationDetails(String customizationDetails) {
        this.customizationDetails = customizationDetails;
    }
}
