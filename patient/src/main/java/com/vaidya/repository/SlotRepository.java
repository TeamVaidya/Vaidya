package com.vaidya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaidya.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}