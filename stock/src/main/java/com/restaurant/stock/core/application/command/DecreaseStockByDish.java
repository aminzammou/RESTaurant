package com.restaurant.stock.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DecreaseStockByDish {
    private UUID dishId;
}
