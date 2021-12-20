package com.restaurant.order.core.domain.event;

import com.restaurant.order.core.domain.OrderLine;

import java.util.List;

public class OrderStatusCanceled extends OrderEvent {
    private final List<OrderLine> orders;
    private final String status;

    public OrderStatusCanceled(List<OrderLine> orders, String status) {
        this.orders = orders;
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return "order.canceled";
    }
}
