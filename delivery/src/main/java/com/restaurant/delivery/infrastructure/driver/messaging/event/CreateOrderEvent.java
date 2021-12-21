package com.restaurant.delivery.infrastructure.driver.messaging.event;


import com.restaurant.delivery.infrastructure.driver.messaging.data.OrderLine;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class CreateOrderEvent {
    public UUID eventId;
    public Instant eventDate;
    public String status;
    public List<OrderLine> orders;
}
