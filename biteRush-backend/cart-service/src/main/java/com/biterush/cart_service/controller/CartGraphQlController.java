package com.biterush.cart_service.controller;

import com.biterush.cart_service.model.entity.Cart;
import com.biterush.cart_service.model.entity.CartItem;
import com.biterush.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CartGraphQlController {

    private final CartService cartService;

    @QueryMapping
    public Cart getByUserId(@Argument UUID userId) {
        return cartService.getCartByUserId(userId);
    }
    @QueryMapping
    public List<CartItem> getItemByCartId(@Argument UUID cartId) {
        return cartService.getCartItemByCartId(cartId);
    }

}
