package com.ecom.order.feignclient;

import com.ecom.order.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "User", url = "http://localhost:2002/")
public interface UserClient {

    @GetMapping(value = "user/getById/{id}")
    public User getUserById(@PathVariable("id") Long id);
}
