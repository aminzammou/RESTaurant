package com.restaurant.order.core.application.command;
import lombok.Getter;

import java.util.List;

public record CreateOrder (
        String name, String email, String note,
    List<CreateOrderLine> orderLineList, String streetName, int houseNumber, String postalCode, String city, String token
) {}
