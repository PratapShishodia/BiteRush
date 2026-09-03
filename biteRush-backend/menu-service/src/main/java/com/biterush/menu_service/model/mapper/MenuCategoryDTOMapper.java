package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuCategoryRequestDTO;
import com.biterush.menu_service.model.entity.MenuCategory;

public class MenuCategoryDTOMapper {

    public static MenuCategory toEntity(MenuCategoryRequestDTO requestDTO){
        return MenuCategory.builder()
                .categoryName(requestDTO.getCategoryName())
                .categoryDescription(requestDTO.getCategoryDescription())
                .restaurantId(requestDTO.getRestaurantId())
                .isActive(requestDTO.getIsActive())
                .build();
    }

}
