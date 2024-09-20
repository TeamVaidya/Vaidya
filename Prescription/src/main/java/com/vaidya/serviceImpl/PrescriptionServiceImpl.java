package com.vaidya.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaidya.entity.Doctor;
import com.vaidya.entity.Patient;
import com.vaidya.entity.Prescription;
import com.vaidya.entity.Slot;
import com.vaidya.repository.DoctorRepository;
import com.vaidya.repository.PatientRepository;
import com.vaidya.repository.PrescriptionRepository;
import com.vaidya.repository.SlotRepository;
import com.vaidya.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	 @Autowired
	    private PrescriptionRepository prescriptionRepository;

	    @Autowired
	    private DoctorRepository doctorRepository;

	    @Autowired
	    private SlotRepository slotRepository;

	    @Autowired
	    private PatientRepository patientRepository;
    @Override
    public Prescription createPrescription(Prescription prescription) {
        // Fetch associated entities
        if (prescription.getDoctor() != null && prescription.getDoctor().getUserId() != null) {
            Doctor doctor = doctorRepository.findById(prescription.getDoctor().getUserId())
                                             .orElseThrow(() -> new RuntimeException("Doctor not found"));
            prescription.setDoctor(doctor);
        }

        if (prescription.getSlot() != null && prescription.getSlot().getSlotId() != null) {
            Slot slot = slotRepository.findById(prescription.getSlot().getSlotId())
                                      .orElseThrow(() -> new RuntimeException("Slot not found"));
            prescription.setSlot(slot);
        }

        if (prescription.getPatient() != null && prescription.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(prescription.getPatient().getPatientId())
                                               .orElseThrow(() -> new RuntimeException("Patient not found"));
            prescription.setPatient(patient);
        }

        return prescriptionRepository.save(prescription);
    }


    @Override
    public Prescription updatePrescription(Long prescriptionId, Prescription prescriptionDetails) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);

        if (optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            prescription.setFever(prescriptionDetails.getFever());
            prescription.setWeight(prescriptionDetails.getWeight());
            prescription.setBp(prescriptionDetails.getBp());
            prescription.setSugar(prescriptionDetails.getSugar());
            prescription.setTest(prescriptionDetails.getTest());
            prescription.setMedicine(prescriptionDetails.getMedicine());
            prescription.setHistory(prescriptionDetails.getHistory());
            prescription.setDoctor(prescriptionDetails.getDoctor());
            prescription.setSlot(prescriptionDetails.getSlot());
            prescription.setPatient(prescriptionDetails.getPatient());
            return prescriptionRepository.save(prescription);
        }

        return null; // Handle exception as needed
    }

    @Override
    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).orElse(null); // Handle not found case as needed
    }

    @Override
    public void deletePrescription(Long prescriptionId) {
        prescriptionRepository.deleteById(prescriptionId);
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }
}
