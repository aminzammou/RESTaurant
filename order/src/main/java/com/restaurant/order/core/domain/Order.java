package com.restaurant.order.core.domain;

import com.restaurant.order.core.domain.event.OrderEvent;
import com.restaurant.order.core.domain.event.OrderStatusChanged;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Document
@Getter
@Setter
public class Order {
    @Id
    private OrderID id;

    private String name;
    private String email;
    private LocalDate orderDateTime;
    private List<OrderLine> orderLines;
    private Double totalPrice;
    private String note;
    private OrderStatus orderStatus;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    public Order(String name, String email, String note, List<OrderLine> orderLines) {
        this.id = new OrderID(UUID.randomUUID());
        this.orderDateTime = LocalDate.now();
        this.orderLines = orderLines;
        this.totalPrice = calculateTotalPrice(orderLines);
        this.name = name;
        this.email = email;
        this.note = note;
        changeStatus(OrderStatus.BeingPrepared);
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.events.add(new OrderStatusChanged(orderLines,orderStatus.toString()));
    }

    public Double calculateTotalPrice(List<OrderLine> orderLines){
        Double totalPrice = 0.0;
        for (OrderLine price : orderLines){
            totalPrice += price.getTotalPrice();
        }
        return totalPrice;
    }

    public void clearEvents() {
        this.events.clear();
    }
    public List<OrderEvent> listEvents() {
        return events;
    }
}
