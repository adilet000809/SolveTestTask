package com.example.solvetesttask.repository;

import com.example.solvetesttask.model.CoffeeType;
import com.example.solvetesttask.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByNameAndCoffeeType(String name, CoffeeType coffeeType);
}
