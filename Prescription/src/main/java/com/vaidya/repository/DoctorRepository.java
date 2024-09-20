package com.vaidya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidya.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
