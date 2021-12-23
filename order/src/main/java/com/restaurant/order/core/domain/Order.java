package com.restaurant.order.core.domain;

import com.restaurant.order.core.domain.event.*;
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
    private Address address;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    public Order(String name, String email, String note, List<OrderLine> orderLines,
                 String streetName, int houseNumber, String postalCode, String city) {
        this.id = new OrderID(UUID.randomUUID());
        this.orderDateTime = LocalDate.now();
        this.orderLines = orderLines;
        this.totalPrice = calculateTotalPrice(orderLines);
        this.name = name;
        this.email = email;
        this.note = note;
        this.address = new Address(streetName, houseNumber, postalCode, city);
        changeStatus(OrderStatus.BeingPrepared);
    }

    public void changeStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        switch (orderStatus) {
            case BeingPrepared -> this.events.add(new OrderStatusBeingPrepared(orderLines, orderStatus.toString()));
            case Deliverd -> this.events.add(new OrderStatusDeliverd(orderLines, orderStatus.toString()));
            case Canceled -> this.events.add(new OrderStatusCanceled(orderLines, orderStatus.toString()));
            case DoneForDelivery -> this.events.add(new OrderStatusDoneForDelivery(id.getId(), orderStatus.toString()));
            default -> {
            }
        }

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
