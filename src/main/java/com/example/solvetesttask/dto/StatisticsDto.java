package com.example.solvetesttask.dto;

import com.example.solvetesttask.model.CoffeeType;
import lombok.Data;

@Data
public class StatisticsDto {
    private CoffeeType coffeeType;
    private int count;
}
