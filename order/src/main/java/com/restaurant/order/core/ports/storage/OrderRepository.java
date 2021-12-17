package com.restaurant.order.core.ports.storage;

import com.restaurant.order.core.domain.Order;
import com.restaurant.order.core.domain.OrderID;
import com.restaurant.order.infrasructure.driven.storage.MongodbOrderRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends MongodbOrderRepository {
    Optional<Order> findByOrderId(UUID id);
}
