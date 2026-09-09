package com.biterush.cart_service.model.dto.response;

import com.biterush.cart_service.model.enums.STATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDTO {
    private UUID cartId;
    private UUID userId;
    private UUID restaurantId;
    private STATUS status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartItemResponseDTO> cartItems;
}
