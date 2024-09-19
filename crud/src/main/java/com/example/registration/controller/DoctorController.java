package com.example.registration.controller;

import com.example.registration.entity.User;
import com.example.registration.repository.DoctorRepository;
import com.example.registration.service.DoctorServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorService;

    @PostMapping("/register")

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> registerDoctor(@RequestBody User doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmDoctorAccount(@RequestParam("token") String confirmationToken) {
        return doctorService.confirmEmail(confirmationToken);
    }
    
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/all")
    public List<User> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<User> getDoctorById(@PathVariable Long doctorId) {
        Optional<User> doctor = doctorRepository.findById(doctorId);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	/*
	 * @GetMapping("/email") public Doctor getDoctorByEmail(@RequestParam String
	 * email) { return doctorRepository.findByUserEmail(email); }
	 */
    @GetMapping("/email")
    public ResponseEntity<User> getDoctorByEmail(@RequestParam String email) {
        User doctor = doctorService.getDoctorByEmail(email);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
