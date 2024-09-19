package com.example.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registration.entity.ConfirmTokenDoctor;

@Repository("confirmTokenDoctorRepo")
public interface ConfirmTokenDoctorRepo extends JpaRepository<ConfirmTokenDoctor, Long> {

    ConfirmTokenDoctor findByConfirmTokenDoctor(String ConfirmTokenDoctor);

}
