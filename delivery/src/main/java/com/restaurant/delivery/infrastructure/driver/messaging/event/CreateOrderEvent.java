package com.restaurant.delivery.infrastructure.driver.messaging.event;


import java.time.Instant;
import java.util.UUID;

public class CreateOrderEvent {
    public UUID eventId;
    public UUID orderId;
    public Instant eventDate;
    public String status;

}
