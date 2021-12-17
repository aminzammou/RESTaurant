package com.restaurant.order.infrasructure.driven.storage;

import com.restaurant.order.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MongodbOrderRepository extends MongoRepository<Order, UUID>  {
    @Query("{'_id._id': ?0 }")
    Optional<Order> findByOrderId(UUID id);
}
