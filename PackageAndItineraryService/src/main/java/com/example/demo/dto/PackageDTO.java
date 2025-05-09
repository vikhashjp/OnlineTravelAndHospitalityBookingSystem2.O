package com.example.demo.dto;

import java.util.List;

public class PackageDTO {
    private Long id;
    private String name;
    private List<String> includedHotels;
    private List<String> includedFlights;
    private List<String> activities;
    private Double price;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getIncludedHotels() {
        return includedHotels;
    }
    public void setIncludedHotels(List<String> includedHotels) {
        this.includedHotels = includedHotels;
    }
    public List<String> getIncludedFlights() {
        return includedFlights;
    }
    public void setIncludedFlights(List<String> includedFlights) {
        this.includedFlights = includedFlights;
    }
    public List<String> getActivities() {
        return activities;
    }
    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
