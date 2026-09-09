package com.biterush.cart_service.repository;

import com.biterush.cart_service.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUserId(UUID userId);
}
