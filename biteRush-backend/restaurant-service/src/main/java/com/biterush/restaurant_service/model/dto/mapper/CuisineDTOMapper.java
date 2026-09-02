package com.biterush.restaurant_service.model.dto.mapper;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.entity.Cuisine;

public class CuisineDTOMapper {

    public static Cuisine toEntity(CuisineRequestDTO dto) {
        return Cuisine.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .isActive(dto.getIsActive())
                .build();
    }

}
