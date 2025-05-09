package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface IUserService {
    User registerUser(User user);
    User getUserByEmail(String email);
    User updateUserRole(Long userId, String role);
    List<User> getAllUsers(); // Additional method if needed
}
