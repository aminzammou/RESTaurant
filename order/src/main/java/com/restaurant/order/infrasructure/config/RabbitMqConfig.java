package com.restaurant.order.infrasructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.order.infrasructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.restaurant}")
    private String restaurantExchangeName;

    @Value("${messaging.queue.order.status}")
    private String orderStatusQueueName;

    @Value("${messaging.queue.order.doneForDelivery}")
    private String orderDeliveryQueueName;

    @Value("${messaging.routing-key.order.status}")
    private String orderStatusRoutingKey;

    @Value("${messaging.routing-key.order.doneForDelivery}")
    private String orderDeliveryRoutingKey;

//    @Value("${messaging.routing-key.being-prepared.order}")
//    private String orderBeingPreparedRoutingKey;
//
//    @Value("${messaging.routing-key.delivered.order}")
//    private String orderDeliveredRoutingKey;
//
//    @Value("${messaging.routing-key.canceled.order}")
//    private String orderCanceledRoutingKey;

    @Value("${messaging.exchange.deadletter}")
    private String deadLetterExchangeName;

    @Value("${messaging.queue.deadletter}")
    private String deadletterQueueName;

    @Value("${messaging.routing-key.deadletter}")
    private String deadLetterRoutingKey;

    @Bean
    public TopicExchange restaurantExchange() {
        return new TopicExchange(restaurantExchangeName);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(deadLetterExchangeName);
    }

    @Bean
    public Queue dlQueue() {
        return QueueBuilder.durable(deadletterQueueName).build();
    }

    @Bean
    public Queue orderStatusQueue() {
        // Creates a new queue in RabbitMQ
        return QueueBuilder.durable(orderStatusQueueName).withArgument("x-dead-letter-exchange", deadLetterExchangeName).withArgument("x-dead-letter-routing-key", deadLetterRoutingKey).build();
    }

    @Bean
    public Queue orderDeliveryQueue() {
        // Creates a new queue in RabbitMQ
        return QueueBuilder.durable(orderDeliveryQueueName).withArgument("x-dead-letter-exchange", deadLetterExchangeName).withArgument("x-dead-letter-routing-key", deadLetterRoutingKey).build();
    }

    @Bean
    public Binding orderStatusBinding() {
        return BindingBuilder
                .bind(orderStatusQueue())
                .to(restaurantExchange())
                .with(orderStatusRoutingKey);
    }

    @Bean
    public Binding orderDeliveryBinding() {
        return BindingBuilder
                .bind(orderDeliveryQueue())
                .to(restaurantExchange())
                .with(orderDeliveryRoutingKey);
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlQueue()).to(deadLetterExchange()).with(deadLetterRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, restaurantExchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }

}
