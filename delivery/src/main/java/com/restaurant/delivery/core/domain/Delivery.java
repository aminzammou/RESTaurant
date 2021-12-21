package com.restaurant.delivery.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private Date departureTime;
    private Date timeDeliverd;
    private boolean isPrepaid;
    private DeliveryStatus deliveryStatus;

//    @Transient
//    private List<DeliveryEvent> events = new ArrayList<>();

    public Delivery(Date departureTime, Date timeDeliverd, boolean isPrepaid) {
        this.id = new DeliveryID(UUID.randomUUID());
        this.departureTime = departureTime;
        this.timeDeliverd = timeDeliverd;
        this.isPrepaid = isPrepaid;
        changeStatus(DeliveryStatus.OnTheWay);
    }

    public void changeStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
//        if (deliveryStatus == DeliveryStatus.Deliverd){
//            this.events.add(new OrderStatusBeingPrepared(orderLines, orderStatus.toString()))
//        }

    }

//    public void clearEvents() {
//        this.events.clear();
//    }
//    public List<DeliveryEvent> listEvents() {
//        return events;
//    }
}
