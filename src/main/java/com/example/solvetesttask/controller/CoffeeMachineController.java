package com.example.solvetesttask.controller;

import com.example.solvetesttask.dto.IngredientDto;
import com.example.solvetesttask.dto.RecipeDto;
import com.example.solvetesttask.dto.StatisticsDto;
import com.example.solvetesttask.model.*;
import com.example.solvetesttask.service.CoffeeMachineService;
import com.example.solvetesttask.service.IngredientService;
import com.example.solvetesttask.service.RecipeService;
import com.example.solvetesttask.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coffee-machine")
@Tag(name = "Coffee Machine API", description = "API for coffee machine management")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final StatisticsService statisticsService;

    public CoffeeMachineController(CoffeeMachineService coffeeMachineService,
                                   RecipeService recipeService,
                                   IngredientService ingredientService,
                                   StatisticsService statisticsService) {
        this.coffeeMachineService = coffeeMachineService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Prepare coffee drink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee is being prepared"),
            @ApiResponse(responseCode = "400", description = "Coffee type or recipe not found"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")})
    @PostMapping("/prepare")
    public ResponseEntity<String> prepareDrink(@RequestParam CoffeeType coffeeType, @RequestParam String recipeName) {
        coffeeMachineService.makeCoffee(coffeeType, recipeName);
        return ResponseEntity.ok(coffeeType + " is being prepared.");
    }

    @Operation(summary = "Add coffee recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe has been added successfully"),
            @ApiResponse(responseCode = "400", description = "Entity not found")})
    @PostMapping("/recipe/add")
    public ResponseEntity<String> addRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.addRecipe(recipeDto);
        return ResponseEntity.ok("Recipe has been added successfully");
    }

    @Operation(summary = "Top up coffee ingredient balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredient has been added successfully")})
    @PostMapping("/ingredient/add")
    public ResponseEntity<String> topUpIngredientBalance(@RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient(null, ingredientDto.getName(), ingredientDto.getQuantity());
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok("Ingredient has been added successfully");
    }

    @Operation(summary = "Get popular coffee drink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Popular coffee drink"),
            @ApiResponse(responseCode = "400", description = "Entity not found")})
    @GetMapping("/popularDrink")
    public ResponseEntity<StatisticsDto> getPopularCoffeeDrink() {
        Statistics statistics = statisticsService.getPopularCoffeeType();
        return ResponseEntity.ok(new StatisticsDto(statistics.getCoffeeType(), statistics.getCount()));
    }
}
