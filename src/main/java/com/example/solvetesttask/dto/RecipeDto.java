package com.example.solvetesttask.dto;

import com.example.solvetesttask.model.CoffeeType;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String name;
    private CoffeeType coffeeType;
    private List<IngredientDto> ingredients;
}
