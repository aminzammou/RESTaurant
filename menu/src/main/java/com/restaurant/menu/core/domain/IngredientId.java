package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class IngredientId {
    private final UUID id;
}
