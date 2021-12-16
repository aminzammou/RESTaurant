package com.restaurant.auth.core.port.messaging;

import com.restaurant.auth.core.domain.event.Event;

public interface EventPublisher {
    void publish(Event event);
}