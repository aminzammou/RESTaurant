package com.restaurant.delivery.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangeDeliveryStatusRequest {
    @NotBlank
    public String status;
}
