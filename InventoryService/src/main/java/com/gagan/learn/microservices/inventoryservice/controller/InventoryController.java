package com.gagan.learn.microservices.inventoryservice.controller;

import com.gagan.learn.microservices.inventoryservice.model.Inventory;
import com.gagan.learn.microservices.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PostMapping
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return inventoryService.updateInventory(inventory);
    }
}
