package com.restaurant.order.core.application.command;

import com.restaurant.order.core.domain.OrderStatus;
import com.restaurant.order.core.domain.event.OrderStatusBeingPrepared;
import com.restaurant.order.core.domain.event.OrderStatusCanceled;
import com.restaurant.order.core.domain.event.OrderStatusDeliverd;
import com.restaurant.order.core.domain.event.OrderStatusDoneForDelivery;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ChangeOrderStatus {
    private final UUID id;
    private final OrderStatus orderStatus;

    public ChangeOrderStatus(UUID id, String Status) {
        this.id = id;

        switch (Status) {
            case "BeingPrepared" -> this.orderStatus = OrderStatus.BeingPrepared;
            case "Deliverd" -> this.orderStatus = OrderStatus.Deliverd;
            case "Canceled" -> this.orderStatus = OrderStatus.Canceled;
            case "DoneForDelivery" -> this.orderStatus = OrderStatus.DoneForDelivery;
            default -> {
                this.orderStatus = null;
            }
        }

    }
}
