package com.vaidya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaidya.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
