package com.restaurant.order.infrasructure.driver.web.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class DeliveryEvent {
    public UUID eventId;
    public Instant eventDate;
    public String status;
    public UUID orderId;
}
