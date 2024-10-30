package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.model.Ingredient;
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
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.getIngredientById(id);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
