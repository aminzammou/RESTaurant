package com.restaurant.stock.core.port.storage;

import com.restaurant.stock.core.domain.Inventory;
import com.restaurant.stock.core.domain.InventoryId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, InventoryId> {
}
