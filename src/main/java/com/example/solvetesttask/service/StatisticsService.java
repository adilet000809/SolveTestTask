package com.example.solvetesttask.service;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;

import java.util.Optional;

public interface StatisticsService {
    Statistics getPopularDrinkType();
    void addOrUpdateStatistics(CoffeeType coffeeType);
}
