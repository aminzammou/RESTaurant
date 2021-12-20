package com.restaurant.order.core.ports.messaging;

import com.restaurant.order.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
