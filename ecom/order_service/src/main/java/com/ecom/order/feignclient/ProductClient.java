package com.ecom.order.feignclient;

import com.ecom.order.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "product-catalog", url = "http://localhost:2000/")
public interface ProductClient {

    @GetMapping(value = "catalog/productById/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId);

}
