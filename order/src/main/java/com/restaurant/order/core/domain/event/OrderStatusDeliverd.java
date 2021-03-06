package com.restaurant.order.core.domain.event;

import com.restaurant.order.core.domain.OrderLine;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderStatusDeliverd extends OrderEvent {
    private final List<OrderLine> orders;
    private final String status;

    public OrderStatusDeliverd(List<OrderLine> orders, String status) {
        this.orders = orders;
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return "order.status.delivered";
    }
}
