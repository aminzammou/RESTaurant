package com.restaurant.order.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreateOrderLine {
    private final UUID dishId;
    private final int amount;

    public CreateOrderLine(String dishId, int amount) {
        this.dishId = UUID.fromString(dishId);
        this.amount = amount;
    }
}
