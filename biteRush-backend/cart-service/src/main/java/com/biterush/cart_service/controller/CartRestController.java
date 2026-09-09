package com.biterush.cart_service.controller;

import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.request.CartRequestDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.model.entity.Cart;
import com.biterush.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<String> intializeCart(@RequestBody CartRequestDTO cartRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.initializeCart(cartRequestDTO));
    }

    @PutMapping("/cart")
    public ResponseEntity<CartResponseDTO> linkCartToRestaurant(@RequestParam UUID userId, @RequestParam UUID restaurantId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartService.linkCartToRestaurant(userId,restaurantId));
    }

    @PutMapping("/clear-cart")
    public ResponseEntity<String> clearCart(@RequestParam UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.clearCart(userId));
    }

    @PostMapping("/cartItem")
    public ResponseEntity<String> addCartItem(@RequestBody CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addCartItem(cartItemRequestDTO));
    }

    @DeleteMapping("/cartItem")
    public ResponseEntity<String> deleteCartItem(@RequestParam UUID cartItemId) {
        return ResponseEntity.ok(cartService.removeCartItem(cartItemId));
    }

    @PutMapping("/cartItem")
    public ResponseEntity<String>  updateCartItem(@RequestParam UUID cartItemId,@RequestParam Integer cartItemQuantity) {
        return ResponseEntity.ok(cartService.updateCartItem(cartItemId,cartItemQuantity));
    }
}
