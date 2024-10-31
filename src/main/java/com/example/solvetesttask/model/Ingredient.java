package com.example.solvetesttask.model;

import com.example.solvetesttask.exception.ServiceUnavailableException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int balance;

    public void withdraw(int amount) {
        if (amount > balance) {
            throw new ServiceUnavailableException("Insufficient amount of an ingredient");
        }
        balance -= amount;
    }
}
