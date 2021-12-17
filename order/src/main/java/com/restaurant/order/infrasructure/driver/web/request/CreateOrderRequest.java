package com.restaurant.order.infrasructure.driver.web.request;

import com.restaurant.order.core.domain.Item;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

//@Value
public class CreateOrderRequest {
//    @NotBlank
//    public List<Item> items;
    @NotBlank
    public String note;
    public List<OrderLineRequest> orderLines;
    public OrderClientRequest client;

    public CreateOrderRequest() {
    }
}
