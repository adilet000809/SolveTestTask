package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findIngredientByName(String name);
}
