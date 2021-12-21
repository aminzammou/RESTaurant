package com.restaurant.delivery.core.application.command;

import com.restaurant.delivery.core.domain.DeliveryStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChangeDeliveryStatus {
    private final UUID id;
    private final DeliveryStatus deliveryStatus;

    public ChangeDeliveryStatus(UUID id, String Status) {
        this.id = id;
        switch (Status) {
            case "OnTheWay" -> this.deliveryStatus = DeliveryStatus.OnTheWay;
            case "Deliverd" -> this.deliveryStatus = DeliveryStatus.Deliverd;
            default -> {
                this.deliveryStatus = null;
            }
        }

    }
}
