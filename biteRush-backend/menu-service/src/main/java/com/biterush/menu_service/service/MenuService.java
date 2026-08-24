package com.biterush.menu_service.service;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemOptionRequestDTO;
import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuCategoryResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemOptionResponseDTO;
import com.biterush.menu_service.model.dto.response.MenuItemResponseDTO;

import java.util.List;
import java.util.UUID;

public interface MenuService {
    List<MenuItemResponseDTO> getMenuItems(UUID restaurantId);
    MenuItemResponseDTO getMenuItem(UUID menuItemId);
    MenuCategoryResponseDTO createMenuCategory(UUID restaurantId,MenuCategoryRequestDTO requestDTO);
    MenuCategoryResponseDTO updateMenuCategory(UUID categoryId, MenuCategoryRequestDTO requestDTO);
    String deleteMenuCategory(UUID categoryId);
    MenuItemResponseDTO createMenuItem(UUID restaurantId,MenuItemRequestDTO requestDTO);
    MenuItemResponseDTO updateMenuItem(UUID menuItemId, MenuItemRequestDTO requestDTO);
    String deleteMenuItem(UUID menuItemId);
    String updateMenuItemAvailability(UUID menuItemId,String availableStatus);
    MenuItemOptionResponseDTO createMenuItemOption(MenuItemOptionRequestDTO requestDTO);
    MenuItemOptionResponseDTO updateMenuItemOption(UUID menuItemOptionId, MenuItemOptionRequestDTO requestDTO);
    String deleteMenuItemOption(UUID menuItemOptionId);
}
