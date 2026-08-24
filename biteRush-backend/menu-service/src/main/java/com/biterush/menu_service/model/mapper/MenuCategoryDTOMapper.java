package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuCategoryResponseDTO;
import com.biterush.menu_service.model.entity.MenuCategory;

public class MenuCategoryDTOMapper {
    public static MenuCategoryResponseDTO toDTO(MenuCategory menuCategory) {
        return MenuCategoryResponseDTO.builder()
                .categoryId(menuCategory.getCategoryId())
                .restaurantId(menuCategory.getRestaurantId())
                .categoryName(menuCategory.getCategoryName())
                .categoryDescription(menuCategory.getCategoryDescription())
                .displayOrder(menuCategory.getDisplayOrder())
                .isActive(menuCategory.getIsActive())
                .build();
    }

    public static MenuCategory toEntity(MenuCategoryRequestDTO requestDTO) {
        return MenuCategory.builder()
                .restaurantId(requestDTO.getRestaurantId())
                .categoryName(requestDTO.getCategoryName())
                .categoryDescription(requestDTO.getCategoryDescription())
                .displayOrder(requestDTO.getDisplayOrder())
                .isActive(requestDTO.getIsActive())
                .build();
    }
}
