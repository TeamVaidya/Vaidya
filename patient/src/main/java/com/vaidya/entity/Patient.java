package com.vaidya.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientName;
    private String mobileNo;
    private String email;
    private Long aadharNo;
    private Integer age;
    private LocalDateTime dateTime;
    private String address;
    private Integer roleId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "slotId")
    private Slot slot;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadharNo, address, age, dateTime, doctor, email, mobileNo, patientId, patientName, roleId,
				slot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(aadharNo, other.aadharNo) && Objects.equals(address, other.address)
				&& Objects.equals(age, other.age) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(doctor, other.doctor) && Objects.equals(email, other.email)
				&& Objects.equals(mobileNo, other.mobileNo) && Objects.equals(patientId, other.patientId)
				&& Objects.equals(patientName, other.patientName) && Objects.equals(roleId, other.roleId)
				&& Objects.equals(slot, other.slot);
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", aadharNo=" + aadharNo + ", age=" + age + ", dateTime=" + dateTime
				+ ", address=" + address + ", roleId=" + roleId + ", doctor=" + doctor + ", slot=" + slot + "]";
	}

	public Patient(Long patientId, String patientName, String mobileNo, String email, Long aadharNo, Integer age,
			LocalDateTime dateTime, String address, Integer roleId, Doctor doctor, Slot slot) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.aadharNo = aadharNo;
		this.age = age;
		this.dateTime = dateTime;
		this.address = address;
		this.roleId = roleId;
		this.doctor = doctor;
		this.slot = slot;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
