package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String contactNumber;
}
