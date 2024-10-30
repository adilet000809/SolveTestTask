package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient getIngredientById(Long id);
}
