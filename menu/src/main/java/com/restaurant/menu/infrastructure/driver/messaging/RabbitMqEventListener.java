package com.restaurant.menu.infrastructure.driver.messaging;

import com.restaurant.menu.core.application.MenuCommandHandler;
import com.restaurant.menu.core.application.command.ChangeDishStatus;
import com.restaurant.menu.infrastructure.driver.messaging.event.StockChangeEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final MenuCommandHandler commandHandler;

    public RabbitMqEventListener(MenuCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "${messaging.queue.stock}")
    void listen(StockChangeEvent event) {
        System.out.println(event.eventId);
        System.out.println(event.eventDate.toString());
        System.out.println(event.ingredientId);
        System.out.println(event.stockAmount);
        this.commandHandler.handle(
            new ChangeDishStatus(event.ingredientId, event.stockAmount)
        );

//        switch (event.eventKey) {
//            case "stock.ingredient.changed":
//                this.commandHandler.handle(
//                        new ChangeDishStatus(event.ingredientId, event.stockAmount)
//                );
//                break;
//            case "keywords.job.removed":
//                this.commandHandler.handle(
//                        new UnmatchCandidates(event.job, event.keyword)
//                );
//                break;
//        }
    }
}
