package com.biterush.cart_service.model.dto.request;

import com.biterush.cart_service.model.enums.STATUS;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequestDTO {
    private UUID userId;
    private UUID restaurantId;
    private STATUS status;
}
