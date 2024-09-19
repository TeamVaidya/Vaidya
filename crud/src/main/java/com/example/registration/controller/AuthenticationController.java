package com.example.registration.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.registration.entity.LoginRequest;
import com.example.registration.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Endpoint for doctor login
    @PostMapping("/login/doctor")
    public String loginDoctor(@RequestBody LoginRequest loginRequest) {
        return authenticationService.loginDoctor(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
