package com.bitberush.order_service.model.entity;

import com.bitberush.order_service.model.enums.PAYMENT_STATUS;
import com.bitberush.order_service.model.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private UUID restaurantId;
    private UUID deliveryAddressId;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    @Enumerated(EnumType.STRING)
    private PAYMENT_STATUS paymentStatus;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal discount;
    private BigDecimal total;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;
    private String cancelReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderItem> orderItems;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = STATUS.PLACED;
        this.estimatedDeliveryTime = LocalDateTime.now().minusHours(2);
        this.paymentStatus = PAYMENT_STATUS.PENDING;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
