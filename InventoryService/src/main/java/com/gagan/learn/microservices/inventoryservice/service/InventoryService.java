package com.gagan.learn.microservices.inventoryservice.service;

import com.gagan.learn.microservices.inventoryservice.model.Inventory;
import com.gagan.learn.microservices.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public Inventory getInventoryByProductId(Long productId) {
        // Optional: Call Product Service to verify existence
        productClient.getProductById(productId);
        return inventoryRepository.findByProductId(productId);
    }

    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
