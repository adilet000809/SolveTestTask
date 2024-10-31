package com.example.solvetesttask.dto;

import com.example.solvetesttask.model.CoffeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    private CoffeeType coffeeType;
    private int count;
}
