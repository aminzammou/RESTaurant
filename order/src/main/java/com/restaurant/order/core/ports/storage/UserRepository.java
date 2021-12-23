package com.restaurant.order.core.ports.storage;

public interface UserRepository {
    boolean isLoggedIn(String token);
}
