package com.biterush.cart_service.service;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.request.CartRequestDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.model.entity.Cart;
import com.biterush.cart_service.model.entity.CartItem;

import java.util.List;
import java.util.UUID;

public interface CartService {
    String initializeCart(CartRequestDTO cartRequestDTO);
    CartResponseDTO linkCartToRestaurant(UUID userId,UUID restaurantId);
    String clearCart(UUID userId);
    String addCartItem(CartItemRequestDTO cartItemRequestDTO);
    String removeCartItem(UUID cartItemId);
    String updateCartItem(UUID cartItemId,Integer quantity);
    Cart getCartByUserId(UUID userId);
    List<CartItem> getCartItemByCartId(UUID cartId);
}
