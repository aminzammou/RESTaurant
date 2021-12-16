package com.restaurant.stock.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveStockRequest {
    @NotBlank
    public UUID ingredientId;
    public int amount;
}
