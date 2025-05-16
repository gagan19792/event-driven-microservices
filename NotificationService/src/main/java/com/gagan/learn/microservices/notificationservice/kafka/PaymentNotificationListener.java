package com.gagan.learn.microservices.notificationservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentNotificationListener {

    private final ObjectMapper objectMapper;

    public PaymentNotificationListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payment.success", groupId = "notification-group")
    public void handlePaymentSuccess(ConsumerRecord<String, String> record) {
        try {
            JsonNode json = objectMapper.readTree(record.value());
            Long orderId = json.get("orderId").asLong();
            String transactionId = json.get("transactionId").asText();

            System.out.printf("[Notification] Payment SUCCESS for Order ID %d | Txn: %s%n", orderId, transactionId);
        } catch (Exception e) {
            System.err.println("[Notification] Failed to process payment.success: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "payment.failed", groupId = "notification-group")
    public void handlePaymentFailure(ConsumerRecord<String, String> record) {
        try {
            JsonNode json = objectMapper.readTree(record.value());
            Long orderId = json.get("orderId").asLong();

            System.out.printf("[Notification] Payment FAILED for Order ID %d%n", orderId);
        } catch (Exception e) {
            System.err.println("[Notification] Failed to process payment.failed: " + e.getMessage());
        }
    }
}
