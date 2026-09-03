package com.biterush.menu_service.service;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.entity.MenuCategory;
import com.biterush.menu_service.model.entity.MenuItem;

import java.util.List;
import java.util.UUID;

public interface MenuService {
    String createMenuCategory(MenuCategoryRequestDTO requestDTO);
    String createMenuItem(MenuItemRequestDTO requestDTO);
    String updateMenuItem(UUID itemId,MenuItemRequestDTO requestDTO);
    String updateMenuCategory(UUID categoryId,MenuCategoryRequestDTO requestDTO);
    String deleteMenuItem(UUID itemId);
    String deleteMenuCategory(UUID categoryId);
    MenuItem getMenuItemById(UUID itemId);
    List<MenuItem> getMenuItems(UUID restaurantId);
//    List<MenuItem> getMenuItemsByCategory(UUID categoryId);
    List<MenuCategory> getMenuCategories(UUID restaurantId);
    MenuCategory getMenuCategory(UUID categoryId);
}
