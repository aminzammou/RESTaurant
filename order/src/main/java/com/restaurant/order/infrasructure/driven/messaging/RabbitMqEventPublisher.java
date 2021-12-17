package com.restaurant.order.infrasructure.driven.messaging;

import com.restaurant.order.core.domain.event.OrderEvent;
import com.restaurant.order.core.ports.messaging.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String OrderExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String orderExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.OrderExchange = orderExchange;
    }


    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(OrderExchange, event.getEventKey(), event);
    }
}