package com.restaurant.delivery.core.application.command;

import java.util.Date;
import java.util.List;

public record CreateDelivery(
        Date departureTime, Date timeDeliverd, boolean isPrepaid
        ) {}
