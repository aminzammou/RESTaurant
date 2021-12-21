package com.restaurant.delivery.core.application.query;

import java.util.UUID;

public class GetDeliveryById {
    private final UUID id;

    public GetDeliveryById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
