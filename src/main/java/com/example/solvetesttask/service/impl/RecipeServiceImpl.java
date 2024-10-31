package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.dto.RecipeDto;
import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import com.example.solvetesttask.model.RecipeIngredient;
import com.example.solvetesttask.repository.RecipeRepository;
import com.example.solvetesttask.service.IngredientService;
import com.example.solvetesttask.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Optional<Recipe> getRecipeByNameAndDrinkType(String name, CoffeeType coffeeType) {
        return recipeRepository.findByNameAndCoffeeType(name, coffeeType);
    }

    @Override
    public void addRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe(null, recipeDto.getName(), recipeDto.getCoffeeType(), null);
        Recipe addedRecipe = recipeRepository.save(recipe);
        List<RecipeIngredient> recipeIngredients = recipeDto.getIngredients().stream()
                .map(ingredientDto -> new RecipeIngredient(
                        null,
                        addedRecipe,
                        ingredientService.getIngredientByName(ingredientDto.getName()),
                        ingredientDto.getQuantity()))
                .toList();
        addedRecipe.setRecipeIngredients(recipeIngredients);
        recipeRepository.save(addedRecipe);
    }
}
