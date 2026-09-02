package com.biterush.restaurant_service.model.dto.mapper;

import com.biterush.restaurant_service.model.dto.request.RestaurantRequestDTO;
import com.biterush.restaurant_service.model.entity.Restaurant;

public class RestaurantDTOMapper {

    public static Restaurant toEntity(RestaurantRequestDTO requestDTO) {
        return Restaurant.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .phoneNumber(requestDTO.getPhoneNumber())
                .email(requestDTO.getEmail())
                .addressLine1(requestDTO.getAddressLine1())
                .addressLine2(requestDTO.getAddressLine2())
                .city(requestDTO.getCity())
                .state(requestDTO.getState())
                .country(requestDTO.getCountry())
                .postalCode(requestDTO.getPostalCode())
                .latitude(requestDTO.getLatitude())
                .longitude(requestDTO.getLongitude())
                .rating(requestDTO.getRating())
                .ratingCount(requestDTO.getRatingCount())
                .priceForTwo(requestDTO.getPriceForTwo())
                .deliveryMinTime(requestDTO.getDeliveryMinTime())
                .deliveryMaxTime(requestDTO.getDeliveryMaxTime())
                .isPureVeg(requestDTO.getIsPureVeg())
                .isActive(requestDTO.getIsActive())
                .build();
    }

}
