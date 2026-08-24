package com.biterush.cart_service.model.mapper;

import com.biterush.cart_service.model.dto.request.CartRequestDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.model.entity.Cart;

public class CartDTOMapper {
    public static CartResponseDTO toDTO(Cart cart) {
        return CartResponseDTO.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .restaurantId(cart.getRestaurantId())
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .items(cart.getItems().stream().map(CartItemDTOMapper::toDTO).toList())
                .build();
    }

    public static Cart toEntity(CartRequestDTO requestDTO){
        return Cart.builder()
                .userId(requestDTO.getUserId())
                .restaurantId(requestDTO.getRestaurantId())
                .status(requestDTO.getStatus())
                .build();
    }
}
