package com.restaurant.delivery.infrastructure.driver.web;
import com.restaurant.delivery.core.application.DeliveryCommandHandler;
import com.restaurant.delivery.core.application.DeliveryQueryHandler;
import com.restaurant.delivery.core.application.command.CreateDelivery;
import com.restaurant.delivery.core.application.query.GetDeliveryById;
import com.restaurant.delivery.core.application.query.ListDeliveries;
import com.restaurant.delivery.core.domain.Delivery;
import com.restaurant.delivery.infrastructure.driver.web.request.CreateDeliveryRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryCommandHandler commandHandler;
    private final DeliveryQueryHandler queryHandler;

    public DeliveryController(DeliveryCommandHandler commandHandler, DeliveryQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }


    @PostMapping
    public Delivery registerOrder(@Valid @RequestBody CreateDeliveryRequest request) {
        return this.commandHandler.handle(
                new CreateDelivery(
                        request.departureTime, request.timeDeliverd, request.isPrepaid
                )
        );
    }

//    @PostMapping("/{id}/change-status")
//    public Order changeStatus(@PathVariable UUID id, @Valid @RequestBody ChangeOrderStatusRequest request) {
//        return this.commandHandler.handle(new ChangeOrderStatus(id, request.status));
//    }
//
    @GetMapping("/{id}")
    public Delivery findDeliveryById(@PathVariable String id) {
        return this.queryHandler.handle(new GetDeliveryById(UUID.fromString(id)));
    }

    @GetMapping()
    public List<Delivery> findAllDeliveries(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListDeliveries(orderBy, direction));
    }

//    @DeleteMapping("/{id}")
//    public Order removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
//        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
//    }

}
