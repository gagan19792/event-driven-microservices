package com.gagan.learn.microservices.paymentservice.kafka;

import com.gagan.learn.microservices.paymentservice.model.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventPublisher {
    private final KafkaTemplate<String, Payment> kafkaTemplate;

    public PaymentEventPublisher(KafkaTemplate<String, Payment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentSuccess(Payment payment) {
        kafkaTemplate.send("payment.success", payment.getOrderId().toString(), payment);
    }

    public void publishPaymentFailure(Payment payment) {
        kafkaTemplate.send("payment.failed", payment.getOrderId().toString(), payment);
    }
}
