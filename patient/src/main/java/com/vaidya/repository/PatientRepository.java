package com.vaidya.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidya.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByMobileNo(String mobileNo);
	 Optional<Patient> findBySlot_SlotId(Long slotId);
	 List<Patient> findByDoctorUserId(Long userId);
	 List<Patient> findByDoctorUserIdAndDateTimeBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
