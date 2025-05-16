package com.gagan.learn.microservices.paymentservice.controller;

import com.gagan.learn.microservices.paymentservice.model.Payment;
import com.gagan.learn.microservices.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @GetMapping("/order/{orderId}")
    public Payment getPaymentByOrder(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }
}
