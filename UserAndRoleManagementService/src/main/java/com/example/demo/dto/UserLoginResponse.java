package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class UserLoginResponse {
    private String message;
    private User user;

    public UserLoginResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
