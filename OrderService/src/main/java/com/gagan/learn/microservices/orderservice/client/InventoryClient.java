package com.gagan.learn.microservices.orderservice.client;


import com.gagan.learn.microservices.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/api/inventory/{productId}")
    Object getInventory(@PathVariable("productId") Long productId);
}
