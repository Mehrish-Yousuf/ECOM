package com.ecom.paymentservice.feignclient;


import com.ecom.paymentservice.DTO.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "order-service", url = "http://localhost:2001/")
public interface OrderClient {

    @GetMapping(value = "order/getbyId/{id}")
    public OrderDTO getOrderById(@PathVariable(value = "id") Long orderId);

}
