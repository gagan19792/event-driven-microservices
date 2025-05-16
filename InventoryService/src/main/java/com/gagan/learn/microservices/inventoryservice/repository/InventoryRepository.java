package com.gagan.learn.microservices.inventoryservice.repository;

import com.gagan.learn.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Long productId);
}
