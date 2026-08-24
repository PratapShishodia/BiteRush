package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuItemOptionValueRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuItemOptionValueResponseDTO;
import com.biterush.menu_service.model.entity.MenuItemOptionValue;

public class MenuItemOptionValueDTOMapper {

    public static MenuItemOptionValueResponseDTO toDTO(MenuItemOptionValue itemOptionValue) {
        return MenuItemOptionValueResponseDTO.builder()
                .menuItemOptionId(itemOptionValue.getMenuItemOptionId())
                .Id(itemOptionValue.getMenuItemOptionId())
                .menuItemOptionValueName(itemOptionValue.getMenuItemOptionValueName())
                .additionalPrice(itemOptionValue.getAdditionalPrice())
                .isAvailable(itemOptionValue.getIsAvailable())
                .build();
    }

    public static MenuItemOptionValue toEntity(MenuItemOptionValueRequestDTO requestDTO){
        return MenuItemOptionValue.builder()
                .menuItemOptionId(requestDTO.getMenuItemOptionId())
                .menuItemOptionValueName(requestDTO.getMenuItemOptionValueName())
                .additionalPrice(requestDTO.getAdditionalPrice())
                .isAvailable(requestDTO.getIsAvailable())
                .build();
    }
}
