package com.ecom.orderservice.service;

import com.ecom.orderservice.DTO.OrderDTO;
import com.ecom.orderservice.domain.Order;
import com.ecom.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderDetails(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Order order = orderOptional.get();
        OrderDTO orderDTO = new OrderDTO();
        if (order != null) {
            orderDTO.setId(order.getId());
            orderDTO.setOrderedDate(order.getOrderedDate());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setTotal(order.getTotal());
            orderDTO.setUserId(order.getUserId());

        }

        return orderDTO;


    }
}
