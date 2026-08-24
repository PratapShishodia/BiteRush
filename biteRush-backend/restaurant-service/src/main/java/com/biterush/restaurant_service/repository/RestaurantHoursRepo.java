package com.biterush.restaurant_service.repository;

import com.biterush.restaurant_service.model.entity.RestaurantHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantHoursRepo extends JpaRepository<RestaurantHours, Long> {
    Optional<RestaurantHours> findByRestaurantRestaurantId(UUID restaurantId);
}
