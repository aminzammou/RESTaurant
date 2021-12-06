package com.restaurant.menu.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetDishById {
    private final UUID id;
}
