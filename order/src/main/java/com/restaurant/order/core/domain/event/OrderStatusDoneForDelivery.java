package com.restaurant.order.core.domain.event;

import com.restaurant.order.core.domain.OrderLine;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderStatusDoneForDelivery extends OrderEvent {
    private final UUID orderId;
    private final String status;

    public OrderStatusDoneForDelivery(UUID orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return "order.doneForDelivery";
    }
}
