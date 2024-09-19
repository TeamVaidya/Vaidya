package com.example.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration.entity.User;

public interface DoctorRepository extends JpaRepository<User, Long>{
	 User findByUserEmailIgnoreCase(String emailId);

	    Boolean existsByUserEmail(String email);
	    
	    User findByUserEmail(String email);

		//Doctor findByFullName(String doctorName);
		  Optional<User> findByFullName(String fullName);
	

}
