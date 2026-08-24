package com.biterush.menu_service.repository;

import com.biterush.menu_service.model.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MenuCategoryRepo extends JpaRepository<MenuCategory, UUID> {
}
