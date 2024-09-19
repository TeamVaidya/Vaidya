package com.vaidya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="user")
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    private String userEmail;
    private String specialization;
    private String qualification;
    private int experience;
    private String address;
    private String password;
    private String diseases;
    private boolean isEnabled = false;

    private Integer roleId = 1; // New field for role ID
    private String aadharNo; // New field for Aadhar number
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDiseases() {
		return diseases;
	}
	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public Doctor(Long userId, String fullName, String userEmail, String specialization, String qualification,
			int experience, String address, String password, String diseases, boolean isEnabled, Integer roleId,
			String aadharNo) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userEmail = userEmail;
		this.specialization = specialization;
		this.qualification = qualification;
		this.experience = experience;
		this.address = address;
		this.password = password;
		this.diseases = diseases;
		this.isEnabled = isEnabled;
		this.roleId = roleId;
		this.aadharNo = aadharNo;
	}
	public Doctor() {
		super();
	}

    // Constructor with all fields
    
  
}
