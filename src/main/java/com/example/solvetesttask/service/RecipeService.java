package com.example.solvetesttask.service;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;

public interface RecipeService {
    Recipe getRecipeByNameAndDrinkType(String name, CoffeeType coffeeType);
    void addRecipe(Recipe recipe);
}
