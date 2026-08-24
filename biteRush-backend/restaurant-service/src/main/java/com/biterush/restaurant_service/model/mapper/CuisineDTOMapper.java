package com.biterush.restaurant_service.model.mapper;

import com.biterush.restaurant_service.model.dto.request.CuisineRequestDTO;
import com.biterush.restaurant_service.model.dto.response.CuisineResponseDTO;
import com.biterush.restaurant_service.model.entity.Cuisine;

public class CuisineDTOMapper {

    public static CuisineResponseDTO toDTO(Cuisine cuisine) {
        return CuisineResponseDTO.builder()
                .cuisineId(cuisine.getCuisineId())
                .cuisineName(cuisine.getCuisineName())
                .slug(cuisine.getSlug())
                .imageUrl(cuisine.getImageUrl())
                .isActive(cuisine.getIsActive())
                .build();
    }

    public static Cuisine toEntity(CuisineRequestDTO requestDTO) {
        return Cuisine.builder()
                .cuisineName(requestDTO.getCuisineName())
                .slug(requestDTO.getSlug())
                .imageUrl(requestDTO.getImageUrl())
                .isActive(requestDTO.getIsActive())
                .build();
    }
}
