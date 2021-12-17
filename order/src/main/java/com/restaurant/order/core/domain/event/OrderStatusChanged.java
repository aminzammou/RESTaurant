package com.restaurant.order.core.domain.event;

import com.restaurant.order.core.domain.OrderLine;

import java.util.List;

public class OrderStatusChanged extends OrderEvent {
    private final List<OrderLine> orders;
    private final String status;

    public OrderStatusChanged(List<OrderLine> orders, String status) {
        this.orders = orders;
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return null;
    }
}