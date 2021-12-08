package com.restaurant.authentication.core.port.messaging;

import com.restaurant.authentication.core.domain.event.Event;

public interface EventPublisher {
    void publish(Event event);
}