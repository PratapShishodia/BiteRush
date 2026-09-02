package com.biterush.restaurant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RestaurantCuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID restaurantCuisineId;

    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private UUID restaurantId;

    @Column(name = "cuisine_id", insertable = false, updatable = false)
    private UUID cuisineId;
}