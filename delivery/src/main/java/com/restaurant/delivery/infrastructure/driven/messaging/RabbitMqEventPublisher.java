package com.restaurant.delivery.infrastructure.driven.messaging;

import com.restaurant.delivery.core.domain.event.DeliveryEvent;
import com.restaurant.delivery.core.ports.messaging.DeliveryEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DeliveryEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String deliveryExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String deliveryExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.deliveryExchange = deliveryExchange;
    }


    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(deliveryExchange, event.getEventKey(), event);
    }
}