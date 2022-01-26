package com.ecom.order.service;


import com.ecom.order.domain.Order;

public interface OrderService {
    public Order saveOrder(Order order);

    Order getOrderDEtails(Long orderId);
}
