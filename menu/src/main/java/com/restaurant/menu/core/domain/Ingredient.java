package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ingredient {
    @Id
    private UUID ingredientId;
    private int amount;

    public Ingredient(Ingredient ingredient, int amount) {
        this.ingredientId = ingredient.ingredientId;
        this.amount = amount;
    }

    public Ingredient(UUID ingredientId) {
        this.ingredientId = ingredientId;
    }
}
