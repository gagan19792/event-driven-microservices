package com.gagan.learn.microservices.paymentservice.service;

import com.gagan.learn.microservices.paymentservice.model.Payment;
import com.gagan.learn.microservices.paymentservice.model.PaymentStatus;
import com.gagan.learn.microservices.paymentservice.repository.PaymentRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, Payment> kafkaTemplate;

    public PaymentService(PaymentRepository paymentRepository, KafkaTemplate<String, Payment> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Payment processPayment(Payment payment) {
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus(PaymentStatus.SUCCESS); // Simulate success
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
