package com.restaurant.order.core.application;

import com.restaurant.order.core.application.command.CreateOrder;
import com.restaurant.order.core.application.query.GetOrderById;
import com.restaurant.order.core.application.query.ListOrders;
import com.restaurant.order.core.domain.Order;
import com.restaurant.order.core.domain.exception.OrderNotFound;
import com.restaurant.order.core.ports.storage.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler {
    private final OrderRepository repository;

    public OrderQueryHandler(OrderRepository repository) {
        this.repository = repository;
    }

    public Order handle(GetOrderById query) {
        return this.repository.findByOrderId(query.getId())
                .orElseThrow(() -> new OrderNotFound(query.getId().toString()));
    }

    public List<Order> handle(ListOrders query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }

}
