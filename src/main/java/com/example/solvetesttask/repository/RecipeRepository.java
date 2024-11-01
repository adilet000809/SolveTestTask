package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByNameAndCoffeeType(String name, CoffeeType coffeeType);
}
