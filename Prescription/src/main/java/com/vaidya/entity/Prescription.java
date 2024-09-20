package com.vaidya.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    private Double fever;
    private Double weight;
    private String bp;
    private Double sugar;

    // For storing multiple values in a list
    @ElementCollection
    private List<String> test;

    @ElementCollection
    private List<String> medicine;

    @ElementCollection
    private List<String> history;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "slotId", nullable = false)
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    // Getters and Setters

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Double getFever() {
        return fever;
    }

    public void setFever(Double fever) {
        this.fever = fever;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public List<String> getTest() {
        return test;
    }

    public void setTest(List<String> test) {
        this.test = test;
    }

    public List<String> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<String> medicine) {
        this.medicine = medicine;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bp, doctor, fever, history, medicine, patient, prescriptionId, slot, sugar, test, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prescription other = (Prescription) obj;
        return Objects.equals(bp, other.bp) && Objects.equals(doctor, other.doctor)
                && Objects.equals(fever, other.fever) && Objects.equals(history, other.history)
                && Objects.equals(medicine, other.medicine) && Objects.equals(patient, other.patient)
                && Objects.equals(prescriptionId, other.prescriptionId) && Objects.equals(slot, other.slot)
                && Objects.equals(sugar, other.sugar) && Objects.equals(test, other.test)
                && Objects.equals(weight, other.weight);
    }

    @Override
    public String toString() {
        return "Prescription [prescriptionId=" + prescriptionId + ", fever=" + fever + ", weight=" + weight
                + ", bp=" + bp + ", sugar=" + sugar + ", test=" + test + ", medicine=" + medicine + ", history="
                + history + ", doctor=" + doctor + ", slot=" + slot + ", patient=" + patient + "]";
    }

    public Prescription(Long prescriptionId, Double fever, Double weight, String bp, Double sugar, List<String> test,
            List<String> medicine, List<String> history, Doctor doctor, Slot slot, Patient patient) {
        super();
        this.prescriptionId = prescriptionId;
        this.fever = fever;
        this.weight = weight;
        this.bp = bp;
        this.sugar = sugar;
        this.test = test;
        this.medicine = medicine;
        this.history = history;
        this.doctor = doctor;
        this.slot = slot;
        this.patient = patient;
    }

    public Prescription() {
        super();
    }
}
