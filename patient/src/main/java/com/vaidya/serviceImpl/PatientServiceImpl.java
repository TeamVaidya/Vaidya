package com.vaidya.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaidya.entity.Doctor;
import com.vaidya.entity.Patient;
import com.vaidya.entity.Slot;
import com.vaidya.repository.DoctorRepository;
import com.vaidya.repository.PatientRepository;
import com.vaidya.repository.SlotRepository;
import com.vaidya.service.PatientService;
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    
    private final DoctorRepository doctorRepository;
    
    private final SlotRepository slotRepository;
    
    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, SlotRepository slotRepository) {
        this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.slotRepository = slotRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        // Fetch the Doctor entity
        Doctor doctor = doctorRepository.findById(patient.getDoctor().getUserId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Fetch the Slot entity
        Slot slot = slotRepository.findById(patient.getSlot().getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Check if the slot is available
        if ("no".equals(slot.getStatus())) {
            throw new RuntimeException("Slot is not available");
        }

        // Set Doctor and Slot in Patient entity
        patient.setDoctor(doctor);
        patient.setSlot(slot);

        // Save the Patient entity
        return patientRepository.save(patient);
    }


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    @Override
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setPatientName(patientDetails.getPatientName());
        patient.setMobileNo(patientDetails.getMobileNo());
        patient.setEmail(patientDetails.getEmail());
        patient.setAadharNo(patientDetails.getAadharNo());
        patient.setAge(patientDetails.getAge());
        patient.setDateTime(patientDetails.getDateTime());
        patient.setAddress(patientDetails.getAddress());
        patient.setRoleId(patientDetails.getRoleId());
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    @Override
    public List<Patient> getPatientsByMobileNo(String mobileNo) {
        return patientRepository.findByMobileNo(mobileNo);
    }
    public Optional<Patient> getPatientBySlotId(Long slotId) {
        return patientRepository.findBySlot_SlotId(slotId);
    }
    @Override
    public List<Patient> getPatientsByDoctorUserId(Long userId) {
        return patientRepository.findByDoctorUserId(userId);
    }
    

    @Override
    public List<Patient> getPatientsByDoctorUserIdAndDate(Long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return patientRepository.findByDoctorUserIdAndDateTimeBetween(userId, startOfDay, endOfDay);
    }

}
