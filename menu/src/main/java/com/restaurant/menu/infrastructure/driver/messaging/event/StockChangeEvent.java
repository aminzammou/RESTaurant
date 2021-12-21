package com.restaurant.menu.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class StockChangeEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID ingredientId;
    public int stockAmount;
}
