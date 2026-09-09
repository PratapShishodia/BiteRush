package com.biterush.cart_service.repository;

import com.biterush.cart_service.model.entity.Cart;
import com.biterush.cart_service.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
   void deleteAllByCartCartId(UUID cartId);
   List<CartItem> findByCartCartId(UUID cartId);
}
