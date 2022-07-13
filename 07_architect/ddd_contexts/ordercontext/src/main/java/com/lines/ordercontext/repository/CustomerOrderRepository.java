package com.lines.ordercontext.repository;

import com.lines.ordercontext.model.CustomerOrder;

public interface CustomerOrderRepository {
    void saveCustomerOrder(CustomerOrder order);
}
