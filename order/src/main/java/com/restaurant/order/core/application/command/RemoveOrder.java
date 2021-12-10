package com.restaurant.order.core.application.command;

import java.util.UUID;

public class RemoveOrder {
    private final UUID id;

    public RemoveOrder(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
