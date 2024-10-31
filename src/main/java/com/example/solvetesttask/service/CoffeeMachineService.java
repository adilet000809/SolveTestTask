package com.example.solvetesttask.service;

import com.example.solvetesttask.exception.EntityNotFoundException;
import com.example.solvetesttask.exception.ServiceUnavailableException;
import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineService {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final StatisticsService statisticsService;
    private final HolidayService holidayService;

    public CoffeeMachineService(RecipeService recipeService, IngredientService ingredientService,
                                StatisticsService statisticsService, HolidayService holidayService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.statisticsService = statisticsService;
        this.holidayService = holidayService;
    }

    public void makeCoffee(CoffeeType coffeeType, String recipeName) {
        checkIsCoffeeMachineAvailableToday();
        Recipe recipe = recipeService.getRecipeByNameAndDrinkType(recipeName, coffeeType).orElseThrow(
                () -> new EntityNotFoundException("Recipe not found")
        );
        checkAreIngredientsAvailable(recipe);
        ingredientService.updateIngredientsBalance(recipe);
        statisticsService.addOrUpdateStatistics(coffeeType);
    }

    private void checkIsCoffeeMachineAvailableToday() {
        if (holidayService.isHolidayToday()) {
            throw new ServiceUnavailableException("Coffee machine is not available today");
        }
    }

    private void checkAreIngredientsAvailable(Recipe recipe) {
        if (!ingredientService.areIngredientsAvailable(recipe)) {
            throw new ServiceUnavailableException("Insufficient amount of ingredients");
        }
    }
}
