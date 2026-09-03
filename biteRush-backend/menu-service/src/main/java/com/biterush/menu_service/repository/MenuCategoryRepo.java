package com.biterush.menu_service.repository;

import com.biterush.menu_service.model.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuCategoryRepo extends JpaRepository<MenuCategory, UUID> {
    @Query("""
    SELECT m
    FROM MenuCategory m
    WHERE m.restaurantId = :restaurantId
      AND m.isActive = true
""")
    List<MenuCategory> findActiveByRestaurantId(@Param("restaurantId") UUID restaurantId);

    Boolean existsByCategoryNameAndRestaurantId(String categoryName, UUID restaurantId);
    Boolean existsByCategoryNameAndRestaurantIdNot(String categoryName, UUID restaurantId);
}
