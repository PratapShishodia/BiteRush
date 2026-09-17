package com.biterush.payment_service.model.dto.response;

import com.biterush.payment_service.model.enums.METHOD;
import com.biterush.payment_service.model.enums.STATUS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {
    private UUID paymentId;
    private UUID orderId;
    private BigDecimal amount;
    private UUID userId;
    private String currency;
    private METHOD method;
    private STATUS status;
    private String provider;
    private UUID transactionId;
    private LocalDateTime failedAt;
    private String failedReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
