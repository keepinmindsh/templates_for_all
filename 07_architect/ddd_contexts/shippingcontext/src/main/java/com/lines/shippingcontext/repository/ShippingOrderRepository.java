package com.lines.shippingcontext.repository;

import com.lines.shippingcontext.model.ShippableOrder;

import java.util.Optional;

public interface ShippingOrderRepository {
    Optional<ShippableOrder> findShippableOrder(int orderId);
}
