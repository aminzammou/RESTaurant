package com.restaurant.menu.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetDishById {
    private final UUID id;
}
