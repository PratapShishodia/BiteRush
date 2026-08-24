package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuItemResponseDTO;
import com.biterush.menu_service.model.entity.MenuItem;

public class MenuItemDTOMapper {
    public static MenuItem toEntity(MenuItemRequestDTO menuItemRequestDTO) {
        return MenuItem.builder()
                .restaurantId(menuItemRequestDTO.getRestaurantId())
                .categoryId(menuItemRequestDTO.getCategoryId())
                .menuItemName(menuItemRequestDTO.getMenuItemName())
                .menuItemDescription(menuItemRequestDTO.getMenuItemDescription())
                .imageUrl(menuItemRequestDTO.getImageUrl())
                .price(menuItemRequestDTO.getPrice())
                .discountedPrice(menuItemRequestDTO.getDiscountedPrice())
                .isVeg(menuItemRequestDTO.getIsVeg())
                .isAvailable(menuItemRequestDTO.getIsAvailable())
                .preparationTime(menuItemRequestDTO.getPreparationTime())
                .displayOrder(menuItemRequestDTO.getDisplayOrder())
                .build();
    }

    public static MenuItemResponseDTO toDTO(MenuItem menuItem) {
        return MenuItemResponseDTO.builder()
                .menuItemId(menuItem.getMenuItemId())
                .restaurantId(menuItem.getRestaurantId())
                .categoryId(menuItem.getCategoryId())
                .menuItemName(menuItem.getMenuItemName())
                .menuItemDescription(menuItem.getMenuItemDescription())
                .imageUrl(menuItem.getImageUrl())
                .price(menuItem.getPrice())
                .discountedPrice(menuItem.getDiscountedPrice())
                .isVeg(menuItem.getIsVeg())
                .isAvailable(menuItem.getIsAvailable())
                .preparationTime(menuItem.getPreparationTime())
                .displayOrder(menuItem.getDisplayOrder())
                .createdAt(menuItem.getCreatedAt())
                .updatedAt(menuItem.getUpdatedAt())
                .build();
    }

}
