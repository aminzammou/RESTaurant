package com.restaurant.menu.core.application;

import com.restaurant.menu.core.domain.Dish;
import com.restaurant.menu.core.domain.event.ListDishes;
import com.restaurant.menu.core.port.storage.DishRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {
    private final DishRepository dishRepository;

    public MenuQueryHandler(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> handle(ListDishes query){
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.dishRepository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
