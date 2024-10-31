package com.example.solvetesttask.service;

import com.example.solvetesttask.model.Ingredient;
import com.example.solvetesttask.model.Recipe;

import java.util.Optional;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    void updateIngredient(Recipe recipe);
    boolean areIngredientsAvailable(Recipe recipe);
    Optional<Ingredient> getIngredientByName(String name);
}
