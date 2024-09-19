package com.vaidya.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaidya.entity.Doctor;
import com.vaidya.entity.Slot;
import com.vaidya.repository.DoctorRepository;
import com.vaidya.repository.SlotRepository;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private DoctorRepository doctorRepository;



    // Create slots and prevent overlapping time ranges on the same date
    public List<Slot> createSlots(LocalTime startTime, LocalTime endTime, String slotRange, Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Parse slotRange string (e.g., "10 minutes") into an integer
        int slotMinutes = Integer.parseInt(slotRange.split(" ")[0]); // Assuming the range is always in minutes

        List<Slot> createdSlots = new ArrayList<>();

        // Loop to create multiple slots within the given time range
        LocalTime currentTime = startTime;
        while (!currentTime.plus(slotMinutes - 1, ChronoUnit.MINUTES).isAfter(endTime)) {
            LocalTime slotEndTime = currentTime.plus(slotMinutes - 1, ChronoUnit.MINUTES); // Slot ends after (slotMinutes - 1) minutes

            // Check if there is an overlapping slot for the same doctor on the same date
            boolean overlappingSlotExists = slotRepository.existsByDoctorAndDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                    doctor, date, slotEndTime, currentTime);

            if (overlappingSlotExists) {
                throw new RuntimeException("Cannot create overlapping slot for the same doctor on the same date");
            }

            // Create the slot if no overlap is found
            Slot slot = new Slot();
            slot.setStartTime(currentTime);
            slot.setEndTime(slotEndTime);
            slot.setSlotRange(slotRange);
            slot.setDoctor(doctor);
            slot.setDate(date);  // Adding date for the slot
            slot.setStatus("yes"); // Default availability

            createdSlots.add(slotRepository.save(slot));

            // Move to the next slot start time (exactly one minute after the end of the current slot)
            currentTime = slotEndTime.plus(1, ChronoUnit.MINUTES);
        }

        return createdSlots;
    }
    // Update the slot status
    public Slot updateSlotStatus(Long slotId, String status) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setStatus(status);
        return slotRepository.save(slot);
    }

    // Check if a slot is available
    public boolean isSlotAvailable(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        return "yes".equals(slot.getStatus());
    }
    
    public List<Slot> getSlotsByDate(LocalDate date) {
        return slotRepository.findByDate(date);
    }
    
    public List<Slot> getSlotsByDateAndUserId(LocalDate date, Long userId) {
        return slotRepository.findByDateAndDoctor_UserId(date, userId);
    }

}
