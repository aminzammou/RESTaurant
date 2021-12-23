package com.restaurant.delivery.core.domain;

import com.restaurant.delivery.core.domain.event.DeliveryEvent;
import com.restaurant.delivery.core.domain.event.DeliveryStatusDeliverd;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
public class Delivery {
    @Id
    private DeliveryID id;

    private LocalDateTime departureTime;
    private LocalDateTime timeDeliverd;
    private DeliveryStatus deliveryStatus;
    private UUID orderId;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    public Delivery(UUID orderId) {
        this.id = new DeliveryID(UUID.randomUUID());
        this.departureTime = LocalDateTime.now();
        this.timeDeliverd = null;
        this.orderId = orderId;

        changeStatus(DeliveryStatus.OnTheWay);
    }

    public void changeStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        if (deliveryStatus == DeliveryStatus.Deliverd){
            this.events.add(new DeliveryStatusDeliverd(deliveryStatus.toString(), orderId));
            this.timeDeliverd = LocalDateTime.now();
        }

    }

    public void clearEvents() {
        this.events.clear();
    }
    public List<DeliveryEvent> listEvents() {
        return events;
    }
}
