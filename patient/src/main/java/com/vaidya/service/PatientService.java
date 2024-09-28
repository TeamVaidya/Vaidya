package com.vaidya.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.vaidya.entity.Patient;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
    List<Patient> getPatientsByMobileNo(String mobileNo);
	Optional<Patient> getPatientBySlotId(Long slotId);
	 List<Patient> getPatientsByDoctorUserId(Long userId);
	 List<Patient> getPatientsByDoctorUserIdAndDate(Long userId, LocalDate date);

	}
