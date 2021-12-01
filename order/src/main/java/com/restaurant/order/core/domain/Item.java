package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Item {
    private Double dishPrice;
    private String dishName;
    private Double amount;
    private Double total;


}
