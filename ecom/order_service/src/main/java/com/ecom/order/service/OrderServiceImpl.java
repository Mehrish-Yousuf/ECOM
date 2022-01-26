package com.ecom.order.service;

import com.ecom.order.domain.Order;
import com.ecom.order.repository.OrderRepository;
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
    public Order getOrderDEtails(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

           return orderOptional.get();


    }
}
