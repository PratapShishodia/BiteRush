package com.bitberush.order_service.model.dto.response;

import com.bitberush.order_service.model.entity.OrderItem;
import com.bitberush.order_service.model.enums.PAYMENT_STATUS;
import com.bitberush.order_service.model.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {
    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private UUID restaurantId;
    private UUID deliveryAddressId;
    private STATUS status;
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
    private List<OrderItemResponseDTO> orderItems;
}
