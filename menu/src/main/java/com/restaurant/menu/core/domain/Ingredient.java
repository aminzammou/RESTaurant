package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Document
public class Ingredient {
    @Id
    private UUID ingredientId;
    private String name;
    private int amount;

}
