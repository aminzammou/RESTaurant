package com.restaurant.stock.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddStockRequest {
    @NotBlank
    public String name;
    public int amount;
}
