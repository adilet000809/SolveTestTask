package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics getTopByOrderByCountDesc();
    Optional<Statistics> findStatisticsByCoffeeType(CoffeeType coffeeType);
}
