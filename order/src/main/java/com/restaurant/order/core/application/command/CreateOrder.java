package com.restaurant.order.core.application.command;

import com.restaurant.order.core.domain.Item;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CreateOrder {
//    private final List<Item> items;
    private final String customerNote;

    public CreateOrder(String customerNote) {
//        this.items = items;
        this.customerNote = customerNote;
    }
}
