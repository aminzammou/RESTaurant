package com.restaurant.auth.core.domain.event;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {
    private final UUID eventID = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public UUID getEventID() {
        return eventID;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public abstract String getEventKey();
}