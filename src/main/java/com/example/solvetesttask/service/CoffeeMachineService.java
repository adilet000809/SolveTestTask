package com.example.solvetesttask.service;

import com.example.solvetesttask.exception.ServiceUnavailableException;
import com.example.solvetesttask.model.*;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineService {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final StatisticsService statisticsService;
    private final HolidayService holidayService;

    public CoffeeMachineService(RecipeService recipeService, IngredientService ingredientService, StatisticsService statisticsService, HolidayService holidayService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.statisticsService = statisticsService;
        this.holidayService = holidayService;
    }

    public void makeCoffee(CoffeeType coffeeType, String recipeName) {
        if (holidayService.isHoliday()) {
            throw new ServiceUnavailableException("Coffee machine is unavailable");
        }
        Recipe recipe = recipeService.getRecipeByNameAndDrinkType(recipeName, coffeeType);
        if (!ingredientService.areIngredientsAvailable(recipe)) {
            throw new RuntimeException("Ingredient not available");
        }

        ingredientService.updateIngredient(recipe);

        statisticsService.addOrUpdateStatistics(coffeeType);

    }
}
