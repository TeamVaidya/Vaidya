package com.vaidya.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    private LocalTime startTime;
    private LocalTime endTime;
    private String slotRange;
    private String status = "yes"; // Default value indicating availability
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Doctor doctor;

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getSlotRange() {
		return slotRange;
	}

	public void setSlotRange(String slotRange) {
		this.slotRange = slotRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, doctor, endTime, slotId, slotRange, startTime, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		return Objects.equals(date, other.date) && Objects.equals(doctor, other.doctor)
				&& Objects.equals(endTime, other.endTime) && Objects.equals(slotId, other.slotId)
				&& Objects.equals(slotRange, other.slotRange) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + ", slotRange="
				+ slotRange + ", status=" + status + ", date=" + date + ", doctor=" + doctor + "]";
	}

	public Slot(Long slotId, LocalTime startTime, LocalTime endTime, String slotRange, String status, LocalDate date,
			Doctor doctor) {
		super();
		this.slotId = slotId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotRange = slotRange;
		this.status = status;
		this.date = date;
		this.doctor = doctor;
	}

	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
