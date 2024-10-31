package com.example.solvetesttask.service;

import com.example.solvetesttask.dto.RecipeDto;
import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;

import java.util.Optional;

public interface RecipeService {
    Optional<Recipe> getRecipeByNameAndDrinkType(String name, CoffeeType coffeeType);
    void addRecipe(RecipeDto recipeDto);
}
