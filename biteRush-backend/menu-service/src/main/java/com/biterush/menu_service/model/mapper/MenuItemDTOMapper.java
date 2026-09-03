package com.biterush.menu_service.model.mapper;

import com.biterush.menu_service.model.dto.request.MenuItemRequestDTO;
import com.biterush.menu_service.model.entity.MenuItem;

public class MenuItemDTOMapper {

    public static MenuItem toEntity(MenuItemRequestDTO requestDTO) {
        return MenuItem.builder()
                .restaurantId(requestDTO.getRestaurantId())
                .itemName(requestDTO.getItemName())
                .itemDescription(requestDTO.getItemDescription())
                .itemImage(requestDTO.getItemImage())
                .itemPrice(requestDTO.getItemPrice())
                .discountedPrice(requestDTO.getDiscountedPrice())
                .isVeg(requestDTO.getIsVeg())
                .isAvailable(requestDTO.getIsAvailable())
                .preparationTime(requestDTO.getPreparationTime())
                .build();
    }
}
