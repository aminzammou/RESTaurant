package com.restaurant.delivery.core.application;
import com.restaurant.delivery.core.application.query.GetDeliveryById;
import com.restaurant.delivery.core.application.query.ListDeliveries;
import com.restaurant.delivery.core.domain.Delivery;
import com.restaurant.delivery.core.domain.exception.DeliveryNotFound;
import com.restaurant.delivery.core.ports.storage.DeliveryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryQueryHandler {
    private final DeliveryRepository repository;

    public DeliveryQueryHandler(DeliveryRepository deliveryRepository) {
        this.repository = deliveryRepository;
    }


    public Delivery handle(GetDeliveryById query) {
        return this.repository.findByOrderId(query.getId())
                .orElseThrow(() -> new DeliveryNotFound(query.getId().toString()));
    }

    public List<Delivery> handle(ListDeliveries query) {
        Sort sort = createSort(query.getDeliveryBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }

}
