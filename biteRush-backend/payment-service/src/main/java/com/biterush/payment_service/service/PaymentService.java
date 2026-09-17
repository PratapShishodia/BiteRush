package com.biterush.payment_service.service;

import com.biterush.payment_service.model.dto.request.PaymentRequestDTO;
import com.biterush.payment_service.model.entity.Payment;
import com.biterush.payment_service.model.enums.STATUS;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    String makePayment(PaymentRequestDTO requestDTO);
    String updatePaymentStatus(UUID paymentId, STATUS status);
    String refundPayment(UUID paymentId);
    Payment findByPaymentId(UUID paymentId);
    Payment findByTransactionId(UUID transactionId);
    List<Payment> getAllByUserId(UUID userId);
}
