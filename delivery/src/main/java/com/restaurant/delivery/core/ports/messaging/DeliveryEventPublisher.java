package com.restaurant.delivery.core.ports.messaging;


import com.restaurant.delivery.core.domain.event.DeliveryEvent;

public interface DeliveryEventPublisher {
    void publish(DeliveryEvent event);
}
