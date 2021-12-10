package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class Item {
    private Double dishPrice;
    private String dishName;
    private Double price;
    private UUID dishId;

}
