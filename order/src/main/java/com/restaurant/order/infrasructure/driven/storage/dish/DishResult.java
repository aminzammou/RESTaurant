package com.restaurant.order.infrasructure.driven.storage.dish;

import com.restaurant.order.core.domain.DishID;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DishResult {
    private DishResultId dishId;
//    private
//   List<String> dishId;
    private String name;
    private Double price;
    private String category;
    private boolean isAvailable;
    private int maxAmount;
//    private String ingredients;

//    public String getDishId() {
//        return "d604693a-340e-4e7e-b6ea-862d57a8f3c4";
//    }
}
