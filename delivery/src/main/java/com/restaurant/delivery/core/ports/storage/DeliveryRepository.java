package com.restaurant.delivery.core.ports.storage;

import com.restaurant.delivery.core.domain.Delivery;
import com.restaurant.delivery.infrastructure.driven.storage.MongodbOrderRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends MongodbOrderRepository {
    Optional<Delivery> findByOrderId(UUID id);
}
