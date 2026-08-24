package com.biterush.menu_service.repository;

import com.biterush.menu_service.model.entity.MenuItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuItemOptionRepo extends JpaRepository<MenuItemOption, UUID> {
}
