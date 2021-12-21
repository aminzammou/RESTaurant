package com.restaurant.delivery.infrastructure.driver.messaging;

import com.restaurant.delivery.infrastructure.driver.messaging.event.CreateOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
//    private final StockCommandHandler commandHandler;

//    public RabbitMqEventListener(StockCommandHandler commandHandler) {
//        this.commandHandler = commandHandler;
//    }

//    @RabbitListener(queues = "${messaging.routing-key.order}")
//    void listen(CreateOrderEvent event) throws Exception {
//        System.out.println(event.eventId);
//        System.out.println(event.eventDate.toString());
//        System.out.println(event.status);
//        System.out.println(event.orderId);
//        DoneForDelivery
//    }
}
