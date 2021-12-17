package com.restaurant.stock.infrastructure.driver.messaging;

import com.restaurant.stock.core.application.StockCommandHandler;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final StockCommandHandler commandHandler;

    public RabbitMqEventListener(StockCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    //TODO:  https://github.com/arothuis-hu/example-jobboard/blob/main/candidates/src/main/java/nl/hu/bep3/jobboard/candidates/infrastructure/driver/messaging/RabbitMqEventListener.java
}
