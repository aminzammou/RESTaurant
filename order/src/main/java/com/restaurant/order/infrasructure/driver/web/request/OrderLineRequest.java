package com.restaurant.order.infrasructure.driver.web.request;

import lombok.Value;

@Value
public class OrderLineRequest {
    public int amount;
    public String id;
}
