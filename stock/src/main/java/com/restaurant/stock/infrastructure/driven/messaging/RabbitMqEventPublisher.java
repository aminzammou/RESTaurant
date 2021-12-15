//package com.restaurant.stock.infrastructure.driven.messaging;
//
//import com.restaurant.stock.core.domain.event.StockEvent;
//import com.restaurant.stock.core.port.messaging.StockEventPublisher;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
//public class RabbitMqEventPublisher implements StockEventPublisher {
//    private final RabbitTemplate rabbitTemplate;
//    private final String restaurantExchange;
//
//    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String restaurantExchange) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.restaurantExchange = restaurantExchange;
//    }
//
//    @Override
//    public void publish(StockEvent event) {
//        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
//    }
//}
