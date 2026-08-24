package com.biterush.cart_service.service;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.response.CartItemResponseDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;

import java.util.UUID;

public interface CartService {
    CartResponseDTO getCart(UUID userId);
    CartItemResponseDTO addCartItem(CartItemRequestDTO cartItemRequestDTO);
    CartItemResponseDTO updateCartItem(UUID cartItem,Integer amount);
    String removeCartItem(UUID cartItem);
    String clearCart(UUID userId);
}
