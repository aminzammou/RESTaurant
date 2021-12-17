package com.restaurant.order.core.application.command;
import lombok.Getter;

import java.util.List;

public record CreateOrder (
        String name, String email, String note,
    List<CreateOrderLine> orderLineList
) {}
