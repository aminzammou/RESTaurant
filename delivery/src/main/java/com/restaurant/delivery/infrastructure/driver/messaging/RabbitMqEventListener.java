package com.restaurant.delivery.infrastructure.driver.messaging;

import com.restaurant.delivery.core.application.DeliveryCommandHandler;
import com.restaurant.delivery.core.application.command.CreateDelivery;
import com.restaurant.delivery.infrastructure.driver.messaging.event.CreateOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @RabbitListener(queues = "${messaging.queue.order}")
    void listen(CreateOrderEvent event) throws Exception {
        System.out.println(event.eventId);
        System.out.println(event.eventDate.toString());
        System.out.println(event.status);
        System.out.println(event.orderId);
        this.commandHandler.handle(new CreateDelivery(event.orderId));
    }
}
