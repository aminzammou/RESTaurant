package com.restaurant.order.infrasructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangeOrderStatusRequest {
    @NotBlank
    public String status;
}
