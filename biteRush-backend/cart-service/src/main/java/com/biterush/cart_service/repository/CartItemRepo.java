package com.biterush.cart_service.repository;

import com.biterush.cart_service.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
}
