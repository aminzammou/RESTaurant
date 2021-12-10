package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderLine {
    private Item item;
    private Double totalPrice;
}
