package com.restaurant.stock.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class InventoryId {
    private UUID id;
}
