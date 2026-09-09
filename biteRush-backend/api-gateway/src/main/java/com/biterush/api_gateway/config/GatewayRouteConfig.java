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

                .build();
    }
}