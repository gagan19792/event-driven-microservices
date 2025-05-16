package com.gagan.learn.microservices.orderservice.service;

import com.gagan.learn.microservices.orderservice.client.InventoryClient;
import com.gagan.learn.microservices.orderservice.client.ProductClient;
import com.gagan.learn.microservices.orderservice.model.Order;
import com.gagan.learn.microservices.orderservice.model.OrderItem;
import com.gagan.learn.microservices.orderservice.model.OrderStatus;
import com.gagan.learn.microservices.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderService(OrderRepository orderRepository,
                        ProductClient productClient,
                        InventoryClient inventoryClient,
                        KafkaTemplate<String, Order> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order placeOrder(Order order) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            productClient.getProduct(item.getProductId());
            inventoryClient.getInventory(item.getProductId());

            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            item.setOrder(order);
        }

        order.setTotalAmount(total);
        order.setStatus(OrderStatus.CREATED);

        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send("order.created", savedOrder.getId().toString(), savedOrder);
        return savedOrder;
    }
}