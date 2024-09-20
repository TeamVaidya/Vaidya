package com.vaidya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidya.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
