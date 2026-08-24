package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuItemOptionRequestDTO;
import com.biterush.menu_service.model.dto.response.MenuItemOptionResponseDTO;
import com.biterush.menu_service.model.entity.MenuItemOption;

public class MenuItemOptionDTOMapper {
    public static MenuItemOptionResponseDTO toDTO(MenuItemOption menuItemOption) {
        return MenuItemOptionResponseDTO.builder()
                .menuItemOptionId(menuItemOption.getMenuItemOptionId())
                .menuItemId(menuItemOption.getMenuItemId())
                .menuItemOptionName(menuItemOption.getMenuItemOptionName())
                .menuItemType(menuItemOption.getMenuItemType())
                .isRequired(menuItemOption.getIsRequired())
                .minSelection(menuItemOption.getMinSelection())
                .maxSelection(menuItemOption.getMaxSelection())
                .build();
    }

    public static MenuItemOption toEntity(MenuItemOptionRequestDTO requestDTO) {
        return MenuItemOption.builder()
                .menuItemId(requestDTO.getMenuItemId())
                .menuItemOptionName(requestDTO.getMenuItemOptionName())
                .menuItemType(requestDTO.getMenuItemType())
                .isRequired(requestDTO.getIsRequired())
                .minSelection(requestDTO.getMinSelection())
                .maxSelection(requestDTO.getMaxSelection())
                .build();
    }
}
