package com.example.solvetesttask.service;

import com.example.solvetesttask.factory.CoffeeFactory;
import com.example.solvetesttask.model.Coffee;
import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import com.example.solvetesttask.model.RecipeIngredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeMachineService {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final StatisticsService statisticsService;

    public CoffeeMachineService(RecipeService recipeService, IngredientService ingredientService, StatisticsService statisticsService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.statisticsService = statisticsService;
    }

    public void makeCoffee(CoffeeType coffeeType, String recipeName) {
        Recipe recipe = recipeService.getRecipeByNameAndDrinkType(recipeName, coffeeType);
        if (!ingredientService.areIngredientsAvailable(recipe)) {
            throw new RuntimeException("Ingredient not available");
        }

        recipe.getRecipeIngredients().stream().map()

    }
}
