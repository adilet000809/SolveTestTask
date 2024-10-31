package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    boolean existsByDateAndIsHolidayIsTrue(LocalDate date);
}
