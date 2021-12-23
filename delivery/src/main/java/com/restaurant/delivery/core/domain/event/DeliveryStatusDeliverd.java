package com.restaurant.delivery.core.domain.event;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeliveryStatusDeliverd extends DeliveryEvent {
    private final String status;
    private final UUID orderId;

    public DeliveryStatusDeliverd(String status, UUID orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    @Override
    public String getEventKey() {
        return "delivery.delivered";
    }
}
