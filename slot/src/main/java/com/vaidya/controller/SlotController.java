package com.vaidya.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaidya.entity.Slot;
import com.vaidya.request.SlotRequest;
import com.vaidya.service.SlotService;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    // POST API to create slots
    @PostMapping
    public ResponseEntity<List<Slot>> createSlots(@RequestBody SlotRequest slotRequest) {
        LocalTime startTime = slotRequest.getStartTime();
        LocalTime endTime = slotRequest.getEndTime();
        String slotRange = slotRequest.getSlotRange();
        Long userId = slotRequest.getUserId(); // Changed from userId to doctorId
        LocalDate date = slotRequest.getDate(); // Add date from the request

        List<Slot> slots = slotService.createSlots(startTime, endTime, slotRange, userId, date);
        return ResponseEntity.ok(slots);
    }

    // PUT API to update slot status
    @PutMapping("/{slotId}")
    public ResponseEntity<Slot> updateSlotStatus(
            @PathVariable Long slotId,
            @RequestParam String status) {
        Slot slot = slotService.updateSlotStatus(slotId, status);
        return ResponseEntity.ok(slot);
    }

    // GET API to check if a slot is available
    @GetMapping("/{slotId}/availability")
    public ResponseEntity<Boolean> isSlotAvailable(@PathVariable Long slotId) {
        boolean available = slotService.isSlotAvailable(slotId);
        return ResponseEntity.ok(available);
    }
    
    @GetMapping("/by-date")
    public ResponseEntity<List<Slot>> getSlotsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Slot> slots = slotService.getSlotsByDate(date);
        return ResponseEntity.ok(slots);
    }
    
    @GetMapping("/search")
    public List<Slot> getSlotsByDateAndUserId(
            @RequestParam("date") LocalDate date,
            @RequestParam("userId") Long userId) {
        return slotService.getSlotsByDateAndUserId(date, userId);
    }
   

}
