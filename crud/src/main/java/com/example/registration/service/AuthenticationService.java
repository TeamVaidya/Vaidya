package com.example.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.registration.entity.User;
import com.example.registration.repository.DoctorRepository;

@Service
public class AuthenticationService {

   

    @Autowired
    private DoctorRepository doctorRepository;

    
    

    @Autowired
    private PasswordEncoder passwordEncoder;

   

    public String loginDoctor(String email, String password) {
        // Fetch the doctor by email
        User doctor = doctorRepository.findByUserEmailIgnoreCase(email);
        
        if (doctor == null) {
            return "Invalid credentials"; // Doctor not found
        }

        // Check if the passwords match
        boolean isPasswordMatch = passwordEncoder.matches(password, doctor.getPassword());

        if (isPasswordMatch) {
            if (doctor.isEnabled()) {
                return "Doctor login successful"; // Login successful
            } else {
                return "Doctor is not verified"; // Doctor is not verified
            }
        } else {
            return "Invalid credentials"; // Passwords don't match
        }
    }

}
