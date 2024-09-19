package com.example.vaidya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaidya.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	
	// Basic queries
    List<User> findByUserId(Long userId);
    List<User> findByFullNameContainingIgnoreCase(String fullName);
    List<User> findByUserEmailContainingIgnoreCase(String userEmail);
    List<User> findByAddressContainingIgnoreCase(String address);
    List<User> findByRoleId(Integer roleId);

    // Combinations of fullName, userEmail, and roleId
    List<User> findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCase(String fullName, String userEmail);
    List<User> findByFullNameContainingIgnoreCaseAndRoleId(String fullName, Integer roleId);
    List<User> findByUserEmailContainingIgnoreCaseAndRoleId(String userEmail, Integer roleId);
    List<User> findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(String fullName, String userEmail, Integer roleId);

    // Combinations with address
    List<User> findByAddressContainingIgnoreCaseAndRoleId(String address, Integer roleId);
    List<User> findByAddressContainingIgnoreCaseAndFullNameContainingIgnoreCase(String address, String fullName);
    List<User> findByAddressContainingIgnoreCaseAndUserEmailContainingIgnoreCase(String address, String userEmail);
    List<User> findByAddressContainingIgnoreCaseAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCase(String address, String fullName, String userEmail);
    List<User> findByAddressContainingIgnoreCaseAndFullNameContainingIgnoreCaseAndRoleId(String address, String fullName, Integer roleId);
    List<User> findByAddressContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(String address, String userEmail, Integer roleId);
    List<User> findByAddressContainingIgnoreCaseAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(String address, String fullName, String userEmail, Integer roleId);

    // Combinations with userId
    List<User> findByUserIdAndFullNameContainingIgnoreCase(Long userId, String fullName);
    List<User> findByUserIdAndUserEmailContainingIgnoreCase(Long userId, String userEmail);
    List<User> findByUserIdAndAddressContainingIgnoreCase(Long userId, String address);
    List<User> findByUserIdAndRoleId(Long userId, Integer roleId);

    List<User> findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCase(Long userId, String fullName, String userEmail);
    List<User> findByUserIdAndFullNameContainingIgnoreCaseAndAddressContainingIgnoreCase(Long userId, String fullName, String address);
    List<User> findByUserIdAndFullNameContainingIgnoreCaseAndRoleId(Long userId, String fullName, Integer roleId);
    List<User> findByUserIdAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(Long userId, String userEmail, String address);
    List<User> findByUserIdAndUserEmailContainingIgnoreCaseAndRoleId(Long userId, String userEmail, Integer roleId);
    List<User> findByUserIdAndAddressContainingIgnoreCaseAndRoleId(Long userId, String address, Integer roleId);

    List<User> findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
            Long userId, String fullName, String userEmail, String address, Integer roleId);
	List<User> findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(Long userId,
			String fullName, String userEmail, Integer roleId);
	List<User> findByUserIdAndFullNameContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(Long userId,
			String fullName, String address, Integer roleId);
	List<User> findByUserIdAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(Long userId,
			String userEmail, String address, Integer roleId);
	List<User> findByFullNameContainingIgnoreCaseAndAddressContainingIgnoreCase(String fullName, String address);
	List<User> findByUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(String userEmail, String address);
	List<User> findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
			String fullName, String userEmail, String address, Integer roleId);
	List<User> findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
			Long userId, String fullName, String userEmail, String address);
}