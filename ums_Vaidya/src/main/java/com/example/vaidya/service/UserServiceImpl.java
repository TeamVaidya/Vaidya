package com.example.vaidya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.vaidya.entity.User;
import com.example.vaidya.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(userDetails.getFullName());
            user.setUserEmail(userDetails.getUserEmail());
            user.setSpecialization(userDetails.getSpecialization());
            user.setQualification(userDetails.getQualification());
            user.setExperience(userDetails.getExperience());
            user.setAddress(userDetails.getAddress());
            user.setPassword(userDetails.getPassword());
            user.setDiseases(userDetails.getDiseases());
            user.setEnabled(userDetails.isEnabled());
            user.setRoleId(userDetails.getRoleId());
            user.setAadharNo(userDetails.getAadharNo());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    

    @Override
    public Page<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }
    

    @Override
    public List<User> filterUsers(Long userId, String fullName, String userEmail, String address, Integer roleId) {
        if (userId != null && fullName != null && userEmail != null && address != null && roleId != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
                    userId, fullName, userEmail, address, roleId);
        } else if (userId != null && fullName != null && userEmail != null && address != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
                    userId, fullName, userEmail, address);
        } else if (userId != null && fullName != null && userEmail != null && roleId != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(
                    userId, fullName, userEmail, roleId);
        } else if (userId != null && fullName != null && address != null && roleId != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
                    userId, fullName, address, roleId);
        } else if (userId != null && userEmail != null && address != null && roleId != null) {
            return userRepository.findByUserIdAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
                    userId, userEmail, address, roleId);
        } else if (fullName != null && userEmail != null && address != null && roleId != null) {
            return userRepository.findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCaseAndRoleId(
                    fullName, userEmail, address, roleId);
        } else if (userId != null && fullName != null && userEmail != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCase(
                    userId, fullName, userEmail);
        } else if (userId != null && fullName != null && address != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCaseAndAddressContainingIgnoreCase(
                    userId, fullName, address);
        } else if (userId != null && userEmail != null && roleId != null) {
            return userRepository.findByUserIdAndUserEmailContainingIgnoreCaseAndRoleId(
                    userId, userEmail, roleId);
        } else if (userId != null && address != null && roleId != null) {
            return userRepository.findByUserIdAndAddressContainingIgnoreCaseAndRoleId(
                    userId, address, roleId);
        } else if (userId != null && fullName != null) {
            return userRepository.findByUserIdAndFullNameContainingIgnoreCase(
                    userId, fullName);
        } else if (userId != null && userEmail != null) {
            return userRepository.findByUserIdAndUserEmailContainingIgnoreCase(
                    userId, userEmail);
        } else if (userId != null && address != null) {
            return userRepository.findByUserIdAndAddressContainingIgnoreCase(
                    userId, address);
        } else if (fullName != null && userEmail != null && roleId != null) {
            return userRepository.findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCaseAndRoleId(
                    fullName, userEmail, roleId);
        } else if (fullName != null && userEmail != null) {
            return userRepository.findByFullNameContainingIgnoreCaseAndUserEmailContainingIgnoreCase(
                    fullName, userEmail);
        } else if (fullName != null && address != null) {
            return userRepository.findByFullNameContainingIgnoreCaseAndAddressContainingIgnoreCase(
                    fullName, address);
        } else if (userEmail != null && address != null) {
            return userRepository.findByUserEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
                    userEmail, address);
        } else if (userEmail != null && roleId != null) {
            return userRepository.findByUserEmailContainingIgnoreCaseAndRoleId(
                    userEmail, roleId);
        } else if (address != null && roleId != null) {
            return userRepository.findByAddressContainingIgnoreCaseAndRoleId(
                    address, roleId);
        } else if (userId != null) {
            return userRepository.findByUserId(userId);
        } else if (fullName != null) {
            return userRepository.findByFullNameContainingIgnoreCase(fullName);
        } else if (userEmail != null) {
            return userRepository.findByUserEmailContainingIgnoreCase(userEmail);
        } else if (address != null) {
            return userRepository.findByAddressContainingIgnoreCase(address);
        } else if (roleId != null) {
            return userRepository.findByRoleId(roleId);
        } else {
            return userRepository.findAll(); // No filters, return all users
        }
    }

}
