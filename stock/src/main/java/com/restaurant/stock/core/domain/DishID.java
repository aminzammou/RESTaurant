package com.restaurant.stock.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DishID {
    private UUID id;

    @Override
    public String toString() {
        return id.toString();
    }
}
