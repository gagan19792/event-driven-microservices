package com.gagan.learn.microservices.paymentservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transactionId;
    private LocalDateTime paymentTime = LocalDateTime.now();

}
