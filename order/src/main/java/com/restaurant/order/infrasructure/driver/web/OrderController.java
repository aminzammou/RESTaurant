package com.restaurant.order.infrasructure.driver.web;

import com.restaurant.order.core.application.OrderCommandHandler;
import com.restaurant.order.core.application.OrderQueryHandler;
import com.restaurant.order.core.application.command.ChangeOrderStatus;
import com.restaurant.order.core.application.command.CreateOrder;
import com.restaurant.order.core.application.query.GetOrderById;
import com.restaurant.order.core.application.query.ListOrders;
import com.restaurant.order.core.domain.Order;
import com.restaurant.order.core.ports.storage.OrderRepository;
import com.restaurant.order.infrasructure.driver.web.request.ChangeOrderStatusRequest;
import com.restaurant.order.infrasructure.driver.web.request.CreateOrderRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCommandHandler commandHandler;
    private final OrderQueryHandler queryHandler;
    public OrderController(OrderCommandHandler commandHandler, OrderQueryHandler queryHandler, OrderRepository orderRepository) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public Order registerOrder(@Valid @RequestBody CreateOrderRequest request) {
        return this.commandHandler.handle(
                new CreateOrder(request.customerNote)
        );
    }

    @PostMapping("/{id}/change-status")
    public Order changeStatus(@PathVariable UUID id, @Valid @RequestBody ChangeOrderStatusRequest request) {
        return this.commandHandler.handle(new ChangeOrderStatus(id, request.status));
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable String id) {
        return this.queryHandler.handle(new GetOrderById(UUID.fromString(id)));
    }

    @GetMapping()
    public List<Order> findAllOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new ListOrders(orderBy, direction));
    }

//    @DeleteMapping("/{id}")
//    public Order removeKeyword(@PathVariable UUID id, @Valid @RequestBody KeywordRequest request) {
//        return this.commandHandler.handle(new RemoveKeyword(id, request.keyword));
//    }

}
