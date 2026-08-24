package com.biterush.cart_service.controller;


import com.biterush.cart_service.model.dto.response.CartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(UUID userId) {}

}
