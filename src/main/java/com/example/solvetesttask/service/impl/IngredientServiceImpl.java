package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.exception.EntityNotFoundException;
import com.example.solvetesttask.model.Ingredient;
import com.example.solvetesttask.model.Recipe;
import com.example.solvetesttask.model.RecipeIngredient;
import com.example.solvetesttask.repository.IngredientRepository;
import com.example.solvetesttask.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void updateIngredientsBalance(Recipe recipe) {
        recipe.getRecipeIngredients()
                .stream()
                .map(recipeIngredient -> {
                    recipeIngredient.getIngredient().withdraw(recipeIngredient.getQuantity());
                    return recipeIngredient.getIngredient();
                }).forEach(ingredientRepository::save);
    }

    @Override
    public boolean areIngredientsAvailable(Recipe recipe) {
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
            Ingredient ingredient = ingredientRepository.findIngredientByName(recipeIngredient.getIngredient().getName())
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
            if (ingredient.getBalance() < recipeIngredient.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findIngredientByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
    }
}
