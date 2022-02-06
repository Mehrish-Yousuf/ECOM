package com.ecom.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "product-catalog", url = "http://localhost:2000/")
public interface ProductClient {

//    @GetMapping(value = "catalog/productById/{id}")
//    public Product getProductById(@PathVariable(value = "id") Long productId);

}
