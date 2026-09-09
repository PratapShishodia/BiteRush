package com.biterush.cart_service.model.mapper;

import com.biterush.cart_service.model.dto.request.CartRequestDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.model.entity.Cart;

public class CartDTOMapper {

    public static Cart toEntity(CartRequestDTO cartRequestDTO) {
        return Cart.builder()
                .userId(cartRequestDTO.getUserId())
                .restaurantId(cartRequestDTO.getRestaurantId())
                .status(cartRequestDTO.getStatus())
                .build();
    }

    public static CartResponseDTO toDTO(Cart cart) {
        return CartResponseDTO.builder()
                .cartId(cart.getCartId())
                .restaurantId(cart.getRestaurantId())
                .userId(cart.getUserId())
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

}
