package com.restaurant.order.core.application;

import com.restaurant.order.core.application.command.ChangeOrderStatus;
import com.restaurant.order.core.application.command.CreateOrder;
import com.restaurant.order.core.application.command.RemoveOrder;
import com.restaurant.order.core.domain.Order;
import com.restaurant.order.core.domain.exception.OrderNotFound;
import com.restaurant.order.core.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderCommandHandler {
    private final OrderRepository repository;

    public OrderCommandHandler(OrderRepository repository) {
        this.repository = repository;
    }

    public Order handle(CreateOrder command) {
        Order order = new Order(command.getCustomerNote());

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
        return this.repository.findByOrderId(id)
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
//        List<JobEvent> events = job.listEvents();
//        events.forEach(eventPublisher::publish);
//        job.clearEvents();
        this.repository.save(order);
    }
}
