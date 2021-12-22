package com.restaurant.order.core.application;

import com.restaurant.order.core.application.command.ChangeOrderStatus;
import com.restaurant.order.core.application.command.CreateOrder;
import com.restaurant.order.core.application.command.CreateOrderLine;
import com.restaurant.order.core.domain.Order;
import com.restaurant.order.core.domain.OrderLine;
import com.restaurant.order.core.domain.event.OrderEvent;
import com.restaurant.order.core.domain.exception.OrderNotAvailable;
import com.restaurant.order.core.domain.exception.OrderNotFound;
import com.restaurant.order.core.ports.messaging.OrderEventPublisher;
import com.restaurant.order.core.ports.storage.DishRepository;
import com.restaurant.order.core.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderCommandHandler {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderCommandHandler(OrderRepository orderRepository, DishRepository dishRepository, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order handle(CreateOrder command) throws OrderNotAvailable, OrderNotFound{

        List<OrderLine> orderLines = new ArrayList<>();
        for (CreateOrderLine orderline: command.orderLineList()) {
            OrderLine orderLine2 = new OrderLine(dishRepository.findbyId(orderline.getDishId()).orElseThrow(() -> new OrderNotFound(orderline.getDishId().toString()))
                    ,orderline.getAmount());
            System.out.print(orderLine2.getDish().getMaxAmount());
            System.out.print(orderLine2.getAmount());
            if (orderLine2.getDish().getMaxAmount() > orderLine2.getAmount() && orderLine2.getDish().getMaxAmount() > 0){
                orderLines.add(orderLine2);
            }else {
                throw new OrderNotAvailable(orderLine2.getDish().getDishName() + "is niet genoeg in voorraad hiervoor. het maximale is: " + orderLine2.getDish().getMaxAmount());
            }
        }


        Order order = new Order(command.name(), command.email(), command.note(), orderLines);

        this.publishEventsAndSave(order);

        return order;
    }

    public Order handle(ChangeOrderStatus command) {
        Order order = this.getOrderById(command.getId());
        order.changeStatus(command.getOrderStatus());

        this.publishEventsAndSave(order);

        return order;
    }

    private Order getOrderById(UUID id) {
        return this.orderRepository.findByOrderId(id)
                .orElseThrow(() -> new OrderNotFound(id.toString()));
    }

//    public Order handle(RemoveOrder command) {
//        Order order = this.getOrderById(command.getId());
//
//        order.removeKeyword(command.getKeyword());
//        this.repository.save(candidate);
//
//        return candidate;
//    }

    private void publishEventsAndSave(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();
        this.orderRepository.save(order);
    }
}
