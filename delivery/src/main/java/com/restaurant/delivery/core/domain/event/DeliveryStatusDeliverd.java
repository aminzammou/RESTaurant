package com.restaurant.delivery.core.domain.event;

import lombok.Getter;

@Getter
public class DeliveryStatusDeliverd extends DeliveryEvent {
    private final String status;

    public DeliveryStatusDeliverd(String status) {
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return "delivery.delivered";
    }
}
