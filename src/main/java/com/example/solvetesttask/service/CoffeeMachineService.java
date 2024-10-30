package com.example.solvetesttask.service;

import com.example.solvetesttask.factory.CoffeeFactory;
import com.example.solvetesttask.model.Coffee;
import com.example.solvetesttask.model.CoffeeType;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineService {
    private final CoffeeFactory coffeeFactory;

    public CoffeeMachineService(CoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    public void makeCoffee(CoffeeType coffeeType) {
        Coffee coffee = coffeeFactory.createCoffee(coffeeType);
        coffee.prepare();
    }
}
