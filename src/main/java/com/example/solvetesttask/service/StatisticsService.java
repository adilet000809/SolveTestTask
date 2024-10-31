package com.example.solvetesttask.service;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;

public interface StatisticsService {
    Statistics getPopularCoffeeType();
    void addOrUpdateStatistics(CoffeeType coffeeType);
}
