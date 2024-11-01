package com.example.solvetesttask.service;

import com.example.solvetesttask.model.CoffeeType;

public interface CoffeeMachineService {
    void makeCoffee(CoffeeType coffeeType, String recipeName);
}
