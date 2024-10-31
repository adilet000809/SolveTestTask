package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Optional<Statistics> findTopByOrderByCountDesc();
    Optional<Statistics> findStatisticsByCoffeeType(CoffeeType coffeeType);
}
