package com.biterush.cart_service.model.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequestDTO {
    private UUID menuItemId;
    private Integer quantity;
    private UUID cartId;
}
