package com.biterush.payment_service.model.dto.request;

import com.biterush.payment_service.model.enums.METHOD;
import com.biterush.payment_service.model.enums.STATUS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDTO {
    private UUID orderId;
    private BigDecimal amount;
    private UUID userId;
    private String currency;
    private METHOD method;
    private String provider;
}
