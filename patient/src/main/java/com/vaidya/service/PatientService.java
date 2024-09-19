package com.vaidya.service;

import java.util.List;

import com.vaidya.entity.Patient;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    List<Patient> getPatientsByMobileNo(String mobileNo);
	}
