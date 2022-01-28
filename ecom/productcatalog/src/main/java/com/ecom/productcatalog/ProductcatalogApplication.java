package com.ecom.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ProductcatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductcatalogApplication.class, args);
    }

}
