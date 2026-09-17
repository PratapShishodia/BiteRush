package com.biterush.payment_service.model.entity;

import com.biterush.payment_service.model.enums.METHOD;
import com.biterush.payment_service.model.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;
    private UUID orderId;
    private BigDecimal amount;
    private UUID userId;
    private String currency;
    @Enumerated(EnumType.STRING)
    private METHOD method;
    private STATUS status;
    private String provider;
    private UUID transactionId;
    private LocalDateTime failedAt;
    private String failedReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
