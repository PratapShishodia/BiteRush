package com.biterush.cart_service.model.mapper;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.response.CartItemResponseDTO;
import com.biterush.cart_service.model.entity.CartItem;

public class CartItemDTOMapper {

    public static CartItemResponseDTO toDTO(CartItem cartItem) {
        return CartItemResponseDTO.builder()
                .cartItemId(cartItem.getCartItemId())
                .menuItemId(cartItem.getMenuItemId())
                .itemName(cartItem.getItemName())
                .unitPrice(cartItem.getUnitPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .createdAt(cartItem.getCreatedAt())
                .updatedAt(cartItem.getUpdatedAt())
                .build();
    }

    public static CartItem toEntity(CartItemRequestDTO requestDTO) {
        return CartItem.builder()
                .menuItemId(requestDTO.getMenuItemId())
                .itemName(requestDTO.getItemName())
                .unitPrice(requestDTO.getUnitPrice())
                .quantity(requestDTO.getQuantity())
                .totalPrice(requestDTO.getTotalPrice())
                .build();
    }

}
