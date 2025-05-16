package com.gagan.learn.microservices.paymentservice.kafka;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagan.learn.microservices.paymentservice.model.Payment;
import com.gagan.learn.microservices.paymentservice.model.PaymentStatus;
import com.gagan.learn.microservices.paymentservice.repository.PaymentRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderEventListener {
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;
    private final PaymentEventPublisher eventPublisher;

    public OrderEventListener(PaymentRepository paymentRepository,
                              ObjectMapper objectMapper,
                              PaymentEventPublisher eventPublisher) {
        this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
        this.eventPublisher = eventPublisher;
    }

    @KafkaListener(topics = "order.created", groupId = "payment-group")
    public void handleOrderCreated(ConsumerRecord<String, String> record) {
        try {
            JsonNode orderJson = objectMapper.readTree(record.value());

            Payment payment = new Payment();
            payment.setOrderId(orderJson.get("id").asLong());
            payment.setAmount(new BigDecimal(orderJson.get("totalAmount").asText()));
            payment.setPaymentMethod("CREDIT_CARD");
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setStatus(PaymentStatus.SUCCESS); // Simulated

            paymentRepository.save(payment);
            eventPublisher.publishPaymentSuccess(payment);

            System.out.println("[PaymentService] Payment processed and published for Order ID: " + payment.getOrderId());

        } catch (Exception e) {
            System.err.println("[PaymentService] Failed to process order.created event: " + e.getMessage());
        }
    }
}
