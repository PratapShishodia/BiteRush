package com.biterush.cart_service.controller;


import com.biterush.cart_service.model.dto.request.CartItemRequestDTO;
import com.biterush.cart_service.model.dto.response.CartItemResponseDTO;
import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import com.biterush.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(UUID userId) {
        return ResponseEntity.ok().body(cartService.getCart(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemResponseDTO> addCartItem(@RequestBody CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCartItem(cartItemRequestDTO));
    }

    @PutMapping("/{userId}/items/{amount}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(@PathVariable UUID userId,@PathVariable Integer amount) {
        return ResponseEntity.ok(cartService.updateCartItem(userId,amount));
    }

    @DeleteMapping("/cart/items/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable UUID cartItemId) {
        return ResponseEntity.ok(cartService.removeCartItem(cartItemId));
    }

    @PutMapping("/cart/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable UUID userId) {
        return ResponseEntity.ok(cartService.clearCart(userId));
    }

}
