package com.restaurant.order.infrasructure.driver.web.messaging;

import com.restaurant.order.core.application.OrderCommandHandler;
import com.restaurant.order.core.application.command.ChangeOrderStatus;
import com.restaurant.order.infrasructure.driver.web.messaging.event.DeliveryEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @RabbitListener(queues = "${messaging.queue.delivery}")
    void listen(DeliveryEvent event) throws Exception {
        System.out.println(event.eventId);
        System.out.println(event.eventDate.toString());
        System.out.println(event.status);
        this.commandHandler.handle(new ChangeOrderStatus(event.orderId, event.status));
    }
}
