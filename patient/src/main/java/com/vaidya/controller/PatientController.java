package com.vaidya.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaidya.entity.Patient;
import com.vaidya.service.PatientService;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        // Save the Patient using the service method
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/search1")
    public List<Patient> getPatientsByMobileNo(@RequestParam("mobileNo") String mobileNo) {
        return patientService.getPatientsByMobileNo(mobileNo);
    }
    @GetMapping("/slot/{slotId}")
    public ResponseEntity<Patient> getPatientBySlotId(@PathVariable Long slotId) {
        Optional<Patient> patient = patientService.getPatientBySlotId(slotId);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/doctor/{userId}")
    public ResponseEntity<List<Patient>> getPatientsByDoctorUserId(@PathVariable Long userId) {
        List<Patient> patients = patientService.getPatientsByDoctorUserId(userId);
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(patients);
        }
    }
    
    @GetMapping("/doctor/{userId}/date/{date}")
    public ResponseEntity<List<Patient>> getPatientsByDoctorUserIdAndDate(
        @PathVariable Long userId,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        
        // Trim the date parameter to avoid extra characters
        LocalDate parsedDate = LocalDate.parse(date.trim());
        
        List<Patient> patients = patientService.getPatientsByDoctorUserIdAndDate(userId, parsedDate);
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(patients);
        }
    }

}
