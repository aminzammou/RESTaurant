package com.restaurant.order.core.application.command;

import com.restaurant.order.core.domain.Item;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public record CreateOrder (
        String name, String email, String note,
    List<CreateOrderLine> orderLineList
) {}
