package com.biterush.cart_service.service.impl;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.request.CartRequestDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;

    @Override
    @Transactional
    public String initializeCart(CartRequestDTO cartRequestDTO) {
        Cart cart = CartDTOMapper.toEntity(cartRequestDTO);
        cartRepo.save(cart);
        return "Cart Initialized";
    }

    @Override
    @Transactional
    public CartResponseDTO linkCartToRestaurant(UUID userId, UUID restaurantId) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(()-> new RuntimeException("Cart not found"));
        cart.setRestaurantId(restaurantId);
        return CartDTOMapper.toDTO(cartRepo.save(cart));
    }

    @Override
    @Transactional
    public String clearCart(UUID userId) {
        Cart cart =  cartRepo.findByUserId(userId).orElseThrow(()-> new RuntimeException("Cart not found"));
        cartItemRepo.deleteAllByCartCartId(cart.getCartId());
        return "Cart cleared";
    }

    @Override
    @Transactional
    public String addCartItem(CartItemRequestDTO cartItemRequestDTO) {
        CartItem cartItem = CartItemDTOMapper.toEntity(cartItemRequestDTO);
        cartItem.setCart(cartRepo.findById(cartItemRequestDTO.getCartId()).orElseThrow(()-> new RuntimeException("Cart not found")));
        // Connect To MenuItem using webCLient
        cartItemRepo.save(cartItem);
        return "Cart Item Added";
    }

    @Override
    @Transactional
    public String removeCartItem(UUID cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElseThrow(()-> new RuntimeException("Cart not found"));
        cartItemRepo.delete(cartItem);
        return "Item Removed";
    }

    @Override
    public String updateCartItem(UUID cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepo.findById(cartItemId).orElseThrow(()-> new RuntimeException("Cart not found"));
        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);
        return "Item Updated";
    }

    @Override
    public Cart getCartByUserId(UUID userId) {
        return cartRepo.findByUserId(userId).orElseThrow(()-> new RuntimeException("Cart not found"));
    }

    @Override
    public List<CartItem> getCartItemByCartId(UUID cartId) {
        return cartItemRepo.findByCartCartId(cartId);
    }

}
