package com.restaurant.delivery.infrastructure.driver.messaging.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DishID {
    private UUID id;

    @Override
    public String toString() {
        return id.toString();
    }
}
