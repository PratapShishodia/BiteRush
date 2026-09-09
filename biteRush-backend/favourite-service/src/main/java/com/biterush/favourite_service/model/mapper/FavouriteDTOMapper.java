package com.biterush.favourite_service.model.mapper;

import com.biterush.favourite_service.model.dto.request.FavouriteRequestDTO;
import com.biterush.favourite_service.model.dto.response.FavouriteResponseDTO;
import com.biterush.favourite_service.model.entity.Favourite;

public class FavouriteDTOMapper {

    public static Favourite toEntity(FavouriteRequestDTO requestDTO) {
        return Favourite.builder()
                .userId(requestDTO.getUserId())
                .restaurantId(requestDTO.getRestaurantId())
                .build();
    }

    public static FavouriteResponseDTO toDTO(Favourite entity) {
        return FavouriteResponseDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .restaurantId(entity.getRestaurantId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
