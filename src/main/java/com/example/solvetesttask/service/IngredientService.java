package com.example.solvetesttask.service;

import com.example.solvetesttask.model.Ingredient;
import com.example.solvetesttask.model.Recipe;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    void updateIngredientsBalance(Recipe recipe);
    boolean areIngredientsAvailable(Recipe recipe);
    Ingredient getIngredientByName(String name);
}
