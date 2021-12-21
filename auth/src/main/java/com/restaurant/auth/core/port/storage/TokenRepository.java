package com.restaurant.auth.core.port.storage;

import com.restaurant.auth.core.domain.element.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<String, String> {

}