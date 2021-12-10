package com.restaurant.order.core.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Document
@Getter
@Setter
public class Order {
    @Id
    private OrderID id;

    private LocalDate orderDateTime;
//    private List<Item> items;
    private Double totalPrice;
    private String customerNote;
    private OrderStatus orderStatus;

    public Order(String customerNote) {
        this.id = new OrderID(UUID.randomUUID());
        this.orderDateTime = LocalDate.now();
//        this.items = items;
        this.totalPrice = 0.0;
        this.customerNote = customerNote;
        this.orderStatus = OrderStatus.BeingPrepared;
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
