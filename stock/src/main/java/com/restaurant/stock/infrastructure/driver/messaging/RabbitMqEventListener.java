package com.restaurant.stock.infrastructure.driver.messaging;

import com.restaurant.stock.core.application.StockCommandHandler;
import com.restaurant.stock.core.application.command.AddIngredient;
import com.restaurant.stock.core.application.command.DecreaseStockByDish;
import com.restaurant.stock.core.application.command.IncreaseStockByDish;
import com.restaurant.stock.infrastructure.driver.messaging.data.OrderLine;
import com.restaurant.stock.infrastructure.driver.messaging.event.CreateOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final StockCommandHandler commandHandler;

    public RabbitMqEventListener(StockCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "${messaging.queue.order}")
    void listen(CreateOrderEvent event) throws Exception {
        System.out.println(event.eventId);
        System.out.println(event.eventDate.toString());
        System.out.println(event.status);
        for(int orderLineIndex = 0; orderLineIndex < event.orders.size(); orderLineIndex++) {
            OrderLine orderLine = event.orders.get(orderLineIndex);
            for(int amountIndex = 0; amountIndex < orderLine.getAmount(); amountIndex++) {
                switch (event.eventKey) {
                    case "order.status.beingPrepared" -> this.commandHandler.handle(new DecreaseStockByDish(orderLine.getDish().getId().getId()));
                    case "order.status.canceled" -> this.commandHandler.handle(new IncreaseStockByDish(orderLine.getDish().getId().getId()));
                }
            }
        }
    }
}
