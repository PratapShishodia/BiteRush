package com.biterush.cart_service.model.mapper;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.response.CartItemResponseDTO;
import com.biterush.cart_service.model.entity.CartItem;

public class CartItemDTOMapper {

    public static CartItem toEntity(CartItemRequestDTO cartItemRequestDTO) {
        return CartItem.builder()
                .menuItemId(cartItemRequestDTO.getMenuItemId())
                .quantity(cartItemRequestDTO.getQuantity())
                .build();
    }

    public static CartItemResponseDTO toDTO(CartItem cartItem) {
        return CartItemResponseDTO.builder()
                .cartItemId(cartItem.getCartItemId())
                .menuItemId(cartItem.getMenuItemId())
                .itemName(cartItem.getItemName())
                .quantity(cartItem.getQuantity())
                .unitPrice(cartItem.getUnitPrice())
                .totalPrice(cartItem.getTotalPrice())
                .createdAt(cartItem.getCreatedAt())
                .updatedAt(cartItem.getUpdatedAt())
                .build();
    }

}
