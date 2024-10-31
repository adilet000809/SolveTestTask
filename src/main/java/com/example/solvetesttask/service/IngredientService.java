package com.example.solvetesttask.service;

import com.example.solvetesttask.model.Ingredient;
import com.example.solvetesttask.model.Recipe;

import java.util.Optional;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    boolean areIngredientsAvailable(Recipe recipe);
}
