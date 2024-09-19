package com.vaidya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidya.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByMobileNo(String mobileNo);
}
