package com.restaurant.order.infrasructure.driver.web.request;

import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

//@Value
public class CreateOrderRequest {
    @NotBlank
    public String note;
    public List<OrderLineRequest> orderLines;
    public OrderClientRequest client;

    public CreateOrderRequest() {
    }
}
