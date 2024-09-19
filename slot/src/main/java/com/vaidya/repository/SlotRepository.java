package com.vaidya.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaidya.entity.Doctor;
import com.vaidya.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
	 List<Slot> findByDate(LocalDate date);
	 boolean existsByDoctorAndDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
	            Doctor doctor, LocalDate date, LocalTime slotEndTime, LocalTime currentTime);
	       
	   
	 List<Slot> findByDateAndDoctor_UserId(LocalDate date, Long userId);
}
