package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.model.Ingredient;
import com.example.solvetesttask.model.Recipe;
import com.example.solvetesttask.model.RecipeIngredient;
import com.example.solvetesttask.repository.IngredientRepository;
import com.example.solvetesttask.service.IngredientService;
import org.springframework.stereotype.Service;

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
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public boolean areIngredientsAvailable(Recipe recipe) {
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
            Ingredient ingredient = ingredientRepository.findIngredientByName(recipeIngredient.getIngredient().getName())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));
            return ingredient.getBalance() < recipeIngredient.getQuantity();
        }
        return true;
    }


}
