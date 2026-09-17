package com.biterush.payment_service.model.mapper;

import com.biterush.payment_service.model.dto.request.PaymentRequestDTO;
import com.biterush.payment_service.model.dto.response.PaymentResponseDTO;
import com.biterush.payment_service.model.entity.Payment;

public class PaymentDTOMapper {

    public static PaymentResponseDTO toDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .userId(payment.getUserId())
                .currency(payment.getCurrency())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .provider(payment.getProvider())
                .transactionId(payment.getTransactionId())
                .failedAt(payment.getFailedAt())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }

    public static Payment toEntity(PaymentRequestDTO requestDTO) {
        return Payment.builder()
                .orderId(requestDTO.getOrderId())
                .amount(requestDTO.getAmount())
                .userId(requestDTO.getUserId())
                .currency(requestDTO.getCurrency())
                .method(requestDTO.getMethod())
                .provider(requestDTO.getProvider())
                .build();
    }
}
