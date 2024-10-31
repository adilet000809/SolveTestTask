package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;
import com.example.solvetesttask.repository.StatisticsRepository;
import com.example.solvetesttask.service.StatisticsService;

import java.util.Optional;

public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Statistics getPopularDrinkType() {
        return statisticsRepository.getTopByOrderByCountDesc();
    }

    @Override
    public void addOrUpdateStatistics(CoffeeType coffeeType) {
        Statistics statistics = statisticsRepository.findStatisticsByCoffeeType(coffeeType)
                .orElse(new Statistics(null, coffeeType, 0));
        statistics.incrementCount();
        statisticsRepository.save(statistics);
    }
}
