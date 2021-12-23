package com.restaurant.delivery.infrastructure.driven.storage;

import com.restaurant.delivery.core.domain.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MongodbOrderRepository extends MongoRepository<Delivery, UUID>  {
    @Query("{'_id._id': ?0 }")
    Optional<Delivery> findByOrderId(UUID id);
}
