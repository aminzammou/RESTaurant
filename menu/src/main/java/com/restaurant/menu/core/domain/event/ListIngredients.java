package com.restaurant.menu.core.domain.event;

import lombok.Getter;

@Getter
public class ListIngredients {
    private final String orderBy;
    private final String direction;

    public ListIngredients(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }
}
