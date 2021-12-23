package com.restaurant.delivery.core.application.command;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record CreateDelivery(
        UUID orderId
        ) {}
