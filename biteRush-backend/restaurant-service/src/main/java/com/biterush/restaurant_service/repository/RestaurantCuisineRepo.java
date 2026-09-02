package com.biterush.restaurant_service.repository;

import com.biterush.restaurant_service.model.entity.RestaurantCuisine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantCuisineRepo extends JpaRepository<RestaurantCuisine, UUID> {
    List<RestaurantCuisine> findByRestaurantId(UUID restaurantId);
    List<RestaurantCuisine> findByCuisineId(UUID cuisineId);

}
