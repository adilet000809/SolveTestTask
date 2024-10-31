package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import com.example.solvetesttask.repository.RecipeRepository;
import com.example.solvetesttask.service.RecipeService;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipeByNameAndDrinkType(String name, CoffeeType coffeeType) {
        return recipeRepository.findByNameAndCoffeeType(name, coffeeType);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
