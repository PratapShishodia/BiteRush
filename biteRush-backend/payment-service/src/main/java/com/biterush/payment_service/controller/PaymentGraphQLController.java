package com.biterush.payment_service.controller;

import com.biterush.payment_service.model.entity.Payment;
import com.biterush.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PaymentGraphQLController {

    private final PaymentService paymentService;

    @QueryMapping
    public List<Payment> getAllByUserId(UUID userId) {
        return paymentService.getAllByUserId(userId);
    }

    @QueryMapping
    public Payment getAllByTransactionId(UUID transactionId) {
        return paymentService.findByTransactionId(transactionId);
    }

    @QueryMapping
    public Payment getAllByPaymentId(UUID paymentId) {
        return paymentService.findByPaymentId(paymentId);
    }

}
