package com.lines.shippingcontext.service;

import com.lines.sharedkernel.service.ApplicationService;
import com.lines.shippingcontext.model.Parcel;
import com.lines.shippingcontext.repository.ShippingOrderRepository;

import java.util.Optional;

public interface ShippingService extends ApplicationService {
    void shipOrder(int orderId);

    void listenToOrderEvents();

    Optional<Parcel> getParcelByOrderId(int orderId);

    void setOrderRepository(ShippingOrderRepository orderRepository);
}