package com.example.solvetesttask.factory;

import com.example.solvetesttask.model.*;
import org.springframework.stereotype.Component;

@Component
public class CoffeeFactory {
    public Coffee createCoffee(CoffeeType type) {
        return switch (type) {
            case ESPRESSO -> new Espresso();
            case AMERICANO -> new Americano();
            case CAPPUCCINO -> new Cappuccino();
        };
    }
}
