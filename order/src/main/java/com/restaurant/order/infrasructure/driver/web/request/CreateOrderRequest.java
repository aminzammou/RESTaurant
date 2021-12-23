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
    public String streetName;
    public int houseNumber;
    public String postalCode;
    public String city;
    public String token;

    public CreateOrderRequest() {
    }
}
