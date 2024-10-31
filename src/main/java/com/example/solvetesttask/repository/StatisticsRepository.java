package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics getTopByOrderByCountDesc();
}
