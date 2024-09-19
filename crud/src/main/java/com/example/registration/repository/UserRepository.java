package com.example.registration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registration.entity.UserP;

@Repository
public interface UserRepository extends JpaRepository<UserP, Long> {
}