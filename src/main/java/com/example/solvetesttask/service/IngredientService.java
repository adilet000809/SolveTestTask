package com.example.solvetesttask.service;

import com.example.solvetesttask.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredientById(Long id);
    void addIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
}
