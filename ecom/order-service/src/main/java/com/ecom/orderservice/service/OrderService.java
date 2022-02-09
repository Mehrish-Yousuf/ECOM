package com.ecom.orderservice.service;


import com.ecom.orderservice.DTO.OrderDTO;
import com.ecom.orderservice.domain.Order;

public interface OrderService {
    public Order saveOrder(Order order);

    OrderDTO getOrderDetails(Long orderId);
    Boolean changeStatus(String status, Long orderId);

}
