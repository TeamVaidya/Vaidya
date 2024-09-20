package com.vaidya.service;

import java.util.List;

import com.vaidya.entity.Prescription;

public interface PrescriptionService {
    Prescription createPrescription(Prescription prescription);
    Prescription updatePrescription(Long prescriptionId, Prescription prescription);
    Prescription getPrescriptionById(Long prescriptionId);
    void deletePrescription(Long prescriptionId);
    List<Prescription> getAllPrescriptions();
}