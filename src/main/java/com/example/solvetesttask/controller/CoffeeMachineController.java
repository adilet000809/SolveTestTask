package com.example.solvetesttask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coffee-machine")
public class CoffeeMachineController {

    @PostMapping("/prepare")
    public ResponseEntity<String> prepareDrink(@RequestParam String drinkType) {
        try {
            coffeeMachineService.prepareDrink(drinkType);
            return ResponseEntity.ok(drinkType + " is being prepared.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
