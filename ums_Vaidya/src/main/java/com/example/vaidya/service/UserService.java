package com.example.vaidya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.vaidya.entity.User;


public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);
    
    Page<User> getUsers(int page, int size);
    
    List<User> filterUsers(Long userId, String fullName, String userEmail, String address, Integer roleId);
}