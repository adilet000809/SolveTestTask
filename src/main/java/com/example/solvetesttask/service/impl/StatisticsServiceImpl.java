package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.model.Statistics;
import com.example.solvetesttask.repository.StatisticsRepository;
import com.example.solvetesttask.service.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Statistics getPopularDrinkType() {
        return statisticsRepository.getTopByOrderByCountDesc();
    }
}
