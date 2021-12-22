package com.restaurant.delivery.core.application.query;

public class ListDeliveries {
    private final String orderBy;
    private final String direction;

    public ListDeliveries(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getDeliveryBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
