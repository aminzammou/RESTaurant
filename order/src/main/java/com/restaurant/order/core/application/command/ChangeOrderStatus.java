package com.restaurant.order.core.application.command;

import com.restaurant.order.core.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ChangeOrderStatus {
    private final UUID id;
    private final OrderStatus orderStatus;

    public ChangeOrderStatus(UUID id, String orderStatus) {
        this.id = id;
        if (Objects.equals(orderStatus, "Deliverd")){
            this.orderStatus = OrderStatus.Deliverd;
        }else {
            this.orderStatus = OrderStatus.BeingPrepared;
        }

    }
}
