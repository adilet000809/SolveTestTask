package com.example.solvetesttask.controller;

import com.example.solvetesttask.dto.IngredientDto;
import com.example.solvetesttask.dto.RecipeDto;
import com.example.solvetesttask.dto.StatisticsDto;
import com.example.solvetesttask.exception.ServiceUnavailableException;
import com.example.solvetesttask.model.*;
import com.example.solvetesttask.service.CoffeeMachineService;
import com.example.solvetesttask.service.IngredientService;
import com.example.solvetesttask.service.RecipeService;
import com.example.solvetesttask.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/coffee-machine")
@Tag(name = "Coffee Machine API", description = "API for coffee machine management")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final StatisticsService statisticsService;

    public CoffeeMachineController(CoffeeMachineService coffeeMachineService, RecipeService recipeService, IngredientService ingredientService, StatisticsService statisticsService) {
        this.coffeeMachineService = coffeeMachineService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Prepare coffee drink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee is being prepared"),
            @ApiResponse(responseCode = "404", description = "Coffee type or recipe not found"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")})
    @PostMapping("/prepare")
    public ResponseEntity<String> prepareDrink(@RequestParam String coffeeType, @RequestParam String recipeName) {
        try {
            coffeeMachineService.makeCoffee(CoffeeType.valueOf(coffeeType), recipeName);
            return ResponseEntity.ok(coffeeType + " is being prepared.");
        } catch (ServiceUnavailableException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Add coffee recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe has been added successfully")})
    @PostMapping("/recipe/add")
    public ResponseEntity<String> addRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.addRecipe(new Recipe(null, recipeDto.getName(), recipeDto.getCoffeeType(), null));
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
            Ingredient ingredient = ingredientService.getIngredientByName(ingredientDto.getName()).orElseThrow();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity(ingredientDto.getQuantity());
            recipeIngredients.add(recipeIngredient);
        }
        recipe.setRecipeIngredients(recipeIngredients);
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe.toString());
    }

    @Operation(summary = "Add coffee ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient has been added successfully")})
    @PostMapping("/ingredient/add")
    public ResponseEntity<String> addIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient(null, ingredientDto.getName(), ingredientDto.getQuantity());
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient.toString());
    }

    @Operation(summary = "Get popular coffee drink type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient has been added successfully")})
    @PostMapping("/popularDrink")
    public ResponseEntity<StatisticsDto> getPopularCoffeeDrink() {
        Statistics statistics = statisticsService.getPopularDrinkType();
        StatisticsDto statisticsDto = new StatisticsDto(statistics.getCoffeeType(), statistics.getCount());
        return ResponseEntity.ok(statisticsDto);
    }
}
