package com.bitberush.order_service.model.dto.request;

import com.bitberush.order_service.model.enums.PAYMENT_STATUS;
import com.bitberush.order_service.model.enums.STATUS;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    private UUID userId;
    private UUID restaurantId;
    private UUID deliveryAddressId;
    private STATUS status;
    private PAYMENT_STATUS paymentStatus;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal discount;
    private List<UUID> menuItemIds;
}
