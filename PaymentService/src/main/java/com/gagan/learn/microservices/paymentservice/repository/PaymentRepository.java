package com.gagan.learn.microservices.paymentservice.repository;

import com.gagan.learn.microservices.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
}
