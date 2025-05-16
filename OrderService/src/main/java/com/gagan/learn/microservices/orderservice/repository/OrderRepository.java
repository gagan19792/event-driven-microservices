package com.gagan.learn.microservices.orderservice.repository;

import com.gagan.learn.microservices.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
