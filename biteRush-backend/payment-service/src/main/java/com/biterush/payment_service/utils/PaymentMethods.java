package com.biterush.payment_service.utils;

import com.biterush.payment_service.model.enums.METHOD;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentMethods {
    METHOD getMethod();
    Boolean pay(UUID transactionId, BigDecimal amount);
}
