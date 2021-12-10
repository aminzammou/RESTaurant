package com.restaurant.menu.core.application.query;

import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.DishId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetDisheshByOrder {
    private List<DishId> dishesIdByOrder;
}
