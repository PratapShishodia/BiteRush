package com.biterush.restaurant_service.model.mapper;

import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantResponseDTO;
import com.biterush.restaurant_service.model.entity.Restaurant;

import java.util.Collections;

public class RestaurantDTOMapper {

    public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .ownerId(restaurant.getOwnerId())
                .restaurantName(restaurant.getRestaurantName())
                .slug(restaurant.getSlug())
                .description(restaurant.getDescription())
                .phone(restaurant.getPhone())
                .email(restaurant.getEmail())
                .logoUrl(restaurant.getLogoUrl())
                .coverImageUrl(restaurant.getCoverImageUrl())
                .addressLine1(restaurant.getAddressLine1())
                .addressLine2(restaurant.getAddressLine2())
                .city(restaurant.getCity())
                .state(restaurant.getState())
                .country(restaurant.getCountry())
                .postalCode(restaurant.getPostalCode())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .rating(restaurant.getRating())
                .totalRatings(restaurant.getTotalRatings())
                .priceForTwo(restaurant.getPriceForTwo())
                .deliveryTimeMin(restaurant.getDeliveryTimeMin())
                .deliveryTimeMax(restaurant.getDeliveryTimeMax())
                .isPureVeg(restaurant.getIsPureVeg())
                .isActive(restaurant.getIsActive())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .restaurantHours(restaurant.getRestaurantHours() == null ? Collections.emptyList()
                        :restaurant.getRestaurantHours().stream().map(RestaurantHoursDTOMapper::toDTO).toList())
                .cuisines(restaurant.getCuisines() == null ? Collections.emptyList() : restaurant.getCuisines().stream().map(CuisineDTOMapper::toDTO).toList())
                .build();
    }

    public static Restaurant toEntity(RestaurantRequestDTO restaurantRequestDTO) {
        return Restaurant.builder()
                .ownerId(restaurantRequestDTO.getOwnerId())
                .restaurantName(restaurantRequestDTO.getRestaurantName())
                .slug(restaurantRequestDTO.getSlug())
                .description(restaurantRequestDTO.getDescription())
                .phone(restaurantRequestDTO.getPhone())
                .email(restaurantRequestDTO.getEmail())
                .logoUrl(restaurantRequestDTO.getLogoUrl())
                .coverImageUrl(restaurantRequestDTO.getCoverImageUrl())
                .addressLine1(restaurantRequestDTO.getAddressLine1())
                .addressLine2(restaurantRequestDTO.getAddressLine2())
                .city(restaurantRequestDTO.getCity())
                .state(restaurantRequestDTO.getState())
                .country(restaurantRequestDTO.getCountry())
                .postalCode(restaurantRequestDTO.getPostalCode())
                .latitude(restaurantRequestDTO.getLatitude())
                .longitude(restaurantRequestDTO.getLongitude())
                .priceForTwo(restaurantRequestDTO.getPriceForTwo())
                .deliveryTimeMax(restaurantRequestDTO.getDeliveryTimeMax())
                .deliveryTimeMin(restaurantRequestDTO.getDeliveryTimeMin())
                .isPureVeg(restaurantRequestDTO.getIsPureVeg())
                .build();
    }

}
