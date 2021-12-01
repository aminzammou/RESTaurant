package com.restaurant.order.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Delivery {
    @Id
    private DeliveryID id;
    private Date timeDeliverd;
    private boolean isPrepaid;
}
