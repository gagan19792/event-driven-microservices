package com.gagan.learn.microservices.orderservice.client;


import com.gagan.learn.microservices.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);
}
