package com.biterush.cart_service.service.impl;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.response.CartItemResponseDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.model.entity.Cart;
import com.biterush.cart_service.model.entity.CartItem;
import com.biterush.cart_service.model.mapper.CartDTOMapper;
import com.biterush.cart_service.model.mapper.CartItemDTOMapper;
import com.biterush.cart_service.repository.CartItemRepo;
import com.biterush.cart_service.repository.CartRepo;
import com.biterush.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;

    @Override
    public CartResponseDTO getCart(UUID userId) {
        Cart cart = cartRepo.findByUserId(userId);
        cart.setCreatedAt(LocalDateTime.now());
        return CartDTOMapper.toDTO(cartRepo.save(cart));
    }

    @Override
    public CartItemResponseDTO addCartItem(CartItemRequestDTO cartItemRequestDTO) {
        CartItem cartItem = CartItemDTOMapper.toEntity(cartItemRequestDTO);
        cartItem.setCreatedAt(LocalDateTime.now());
        cartItem.setCart(cartRepo.findByCartId(cartItemRequestDTO.getCartId()));
        return CartItemDTOMapper.toDTO(cartItemRepo.save(cartItem));
    }

    @Override
    public CartItemResponseDTO updateCartItem(UUID cartItemId, Integer amount) {
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart Item not Found"));
        cartItem.setQuantity(amount);
        cartItem.setUpdatedAt(LocalDateTime.now());
        return CartItemDTOMapper.toDTO(cartItemRepo.save(cartItem));
    }

    @Override
    public String removeCartItem(UUID cartItem) {
        cartItemRepo.deleteById(cartItem);
        return "Cart Item Remove";
    }

    @Override
    public String clearCart(UUID userId) {
        Cart cart = cartRepo.findByUserId(userId);
        cart = new Cart();
        cartRepo.save(cart);
        return "Cart Cleared";
    }
}
