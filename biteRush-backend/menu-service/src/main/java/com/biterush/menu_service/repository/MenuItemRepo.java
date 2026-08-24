package com.biterush.menu_service.repository;

import com.biterush.menu_service.model.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findByRestaurantId(UUID restaurantId);
}
