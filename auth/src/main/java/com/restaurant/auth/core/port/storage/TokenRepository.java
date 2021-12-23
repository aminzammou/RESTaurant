package com.restaurant.auth.core.port.storage;

import com.restaurant.auth.core.domain.element.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {

}