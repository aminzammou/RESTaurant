package com.restaurant.authentication.core.port.storage;

import com.restaurant.authentication.core.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}