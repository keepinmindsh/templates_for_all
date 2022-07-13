package com.lines.ordercontext.service;

import com.lines.ordercontext.model.CustomerOrder;
import com.lines.ordercontext.repository.CustomerOrderRepository;
import com.lines.sharedkernel.service.ApplicationService;

public interface OrderService extends ApplicationService {
    void placeOrder(CustomerOrder order);

    void setOrderRepository(CustomerOrderRepository orderRepository);
}