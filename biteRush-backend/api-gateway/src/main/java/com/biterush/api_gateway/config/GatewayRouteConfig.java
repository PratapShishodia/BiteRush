package com.biterush.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))

                .route("user-service", r -> r
                        .path("/api/user/**")
                        .uri("lb://USER-SERVICE"))

                .route("menu-service", r -> r
                        .path("/api/menu/**")
                        .uri("lb://MENU-SERVICE"))

                .route("rating-service", r -> r
                        .path("/api/rating/**")
                        .uri("lb://RATING-SERVICE"))

                .route("restaurant-service", r -> r
                        .path("/api/restaurant/**")
                        .uri("lb://RESTAURANT-SERVICE"))

                .route("cart-service", r -> r
                        .path("/api/cart/**", "/api/cartItem/**", "/api/clear-cart/**")
                        .uri("lb://CART-SERVICE"))

                .route("favourite-service", r -> r
                        .path("/api/favourite/**")
                        .uri("lb://FAVOURITE-SERVICE"))

                .route("order-service", r -> r
                        .path("/api/order/**")
                        .uri("lb://ORDER-SERVICE"))

                .route("payment-service", r -> r
                        .path("/api/payment/**")
                        .uri("lb://PAYMENT-SERVICE"))

                .route("notification-service", r -> r
                        .path("/api/notification/**")
                        .uri("lb://NOTIFICATION-SERVICE"))

                .build();
    }
}