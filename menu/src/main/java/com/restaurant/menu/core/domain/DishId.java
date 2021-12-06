package com.restaurant.menu.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DishId {
    private final UUID id;
}
