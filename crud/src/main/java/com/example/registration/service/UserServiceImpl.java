package com.example.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registration.entity.UserP;
import com.example.registration.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserP registerUser(UserP user) {
        // You can add additional business logic here if necessary
        return userRepository.save(user);
    }
}