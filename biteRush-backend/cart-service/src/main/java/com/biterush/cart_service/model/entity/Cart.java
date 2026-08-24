package com.biterush.cart_service.model.entity;

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
public class Cart {
    private UUID cartId;
    private UUID userId;
    private UUID restaurantId;
    private CART_STATUS status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<CartItem> items;
}
