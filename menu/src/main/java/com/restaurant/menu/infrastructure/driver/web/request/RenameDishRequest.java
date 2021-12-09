package com.restaurant.menu.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class RenameDishRequest {
    @NotBlank
    public String name;
}
