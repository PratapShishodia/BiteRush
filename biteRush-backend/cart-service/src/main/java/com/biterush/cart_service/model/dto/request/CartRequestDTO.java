package com.biterush.cart_service.model.dto.request;

import com.biterush.cart_service.model.entity.CartItem;
import com.biterush.cart_service.model.enums.CART_STATUS;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequestDTO {
    private UUID userId;
    private UUID restaurantId;
    private CART_STATUS status;
}
