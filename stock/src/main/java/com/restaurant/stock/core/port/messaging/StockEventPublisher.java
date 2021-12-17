package com.restaurant.stock.core.port.messaging;

import com.restaurant.stock.core.domain.event.StockEvent;

public interface StockEventPublisher {
    void publish(StockEvent event);
}
