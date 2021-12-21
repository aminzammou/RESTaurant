package com.restaurant.delivery.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

//@Value
public class CreateDeliveryRequest {
    @NotBlank
    public Date departureTime;

    public Date timeDeliverd;
    public boolean isPrepaid;

    public CreateDeliveryRequest() {
    }
}
