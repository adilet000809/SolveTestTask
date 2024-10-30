package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    boolean existsByDateAndIsHolidayTrue(Date date);
}
