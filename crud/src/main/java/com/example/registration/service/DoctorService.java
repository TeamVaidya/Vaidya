package com.example.registration.service;

import org.springframework.http.ResponseEntity;

import com.example.registration.entity.User;

public interface DoctorService {
	ResponseEntity<?> saveDoctor(User doctor);

    ResponseEntity<?> confirmEmail(String confirmTokenDoctor);
    
    User getDoctorByEmail(String email);
}
