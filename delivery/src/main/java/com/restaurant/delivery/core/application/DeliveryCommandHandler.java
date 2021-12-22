package com.restaurant.delivery.core.application;

import com.restaurant.delivery.core.application.command.ChangeDeliveryStatus;
import com.restaurant.delivery.core.application.command.CreateDelivery;
import com.restaurant.delivery.core.domain.Delivery;
import com.restaurant.delivery.core.domain.event.DeliveryEvent;
import com.restaurant.delivery.core.domain.exception.DeliveryNotFound;
import com.restaurant.delivery.core.ports.messaging.DeliveryEventPublisher;
import com.restaurant.delivery.core.ports.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryCommandHandler {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, DeliveryEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }


    public Delivery handle(CreateDelivery command) {

        Delivery delivery = new Delivery(command.timeDeliverd(), command.departureTime(), command.isPrepaid());

        this.publishEventsAndSave(delivery);

        return delivery;
    }

    public Delivery handle(ChangeDeliveryStatus command) {
        Delivery delivery = this.getOrderById(command.getId());
        delivery.changeStatus(command.getDeliveryStatus());

        this.publishEventsAndSave(delivery);

        return delivery;
    }

    private Delivery getOrderById(UUID id) {
        return this.deliveryRepository.findByOrderId(id)
                .orElseThrow(() -> new DeliveryNotFound(id.toString()));
    }

//    public Order handle(RemoveOrder command) {
//        Order order = this.getOrderById(command.getId());
//
//        order.removeKeyword(command.getKeyword());
//        this.repository.save(candidate);
//
//        return candidate;
//    }

    private void publishEventsAndSave(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(eventPublisher::publish);
        delivery.clearEvents();
        this.deliveryRepository.save(delivery);
    }
}
