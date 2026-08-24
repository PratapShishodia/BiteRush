package com.biterush.restaurant_service.repository;

import com.biterush.restaurant_service.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, UUID> {
}
