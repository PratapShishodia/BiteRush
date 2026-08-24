package com.biterush.cart_service.repository;

import com.biterush.cart_service.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepo extends JpaRepository<Cart, UUID> {
    Cart findByCartId(UUID cartId);
    Cart findByUserId(UUID userId);
}
