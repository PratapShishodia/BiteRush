package com.biterush.menu_service.repository;

import com.biterush.menu_service.model.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, UUID> {
    @Query("SELECT m FROM MenuItem m WHERE m.restaurantId = :restaurantId AND m.isAvailable = true")
    List<MenuItem> findByRestaurantIdAndIsAvailable(@Param("restaurantId") UUID restaurantId);

    Boolean existsByItemNameAndRestaurantId(String itemName, UUID restaurantId);
    Boolean existsByItemNameAndRestaurantIdNot(String itemName, UUID restaurantId);

}
