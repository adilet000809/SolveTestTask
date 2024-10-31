package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.exception.EntityNotFoundException;
import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Statistics;
import com.example.solvetesttask.repository.StatisticsRepository;
import com.example.solvetesttask.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Statistics getPopularCoffeeType() {
        return statisticsRepository.findTopByOrderByCountDesc()
                .orElseThrow(() -> new EntityNotFoundException("Popular coffee drink not found or not determined yet"));
    }

    @Override
    public void addOrUpdateStatistics(CoffeeType coffeeType) {
        Statistics statistics = statisticsRepository.findStatisticsByCoffeeType(coffeeType)
                .orElse(new Statistics(null, coffeeType, 0));
        statistics.incrementCount();
        statisticsRepository.save(statistics);
    }
}
