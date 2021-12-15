package com.restaurant.stock.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    private IngredientId id;
    private String name;

    public Ingredient(String name) {
        this.id = new IngredientId(UUID.randomUUID());
        this.name = name;
    }
}

