package com.biterush.restaurant_service.model.mapper;

import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.dto.response.RestaurantHoursResponseDTO;
import com.biterush.restaurant_service.model.entity.RestaurantHours;

public class RestaurantHoursDTOMapper {
    public static RestaurantHoursResponseDTO toDTO(RestaurantHours restaurantHours) {
        return RestaurantHoursResponseDTO.builder()
                .id(restaurantHours.getId())
                .dayOfWeek(restaurantHours.getDayOfWeek())
                .openingTime(restaurantHours.getOpeningTime())
                .closingTime(restaurantHours.getClosingTime())
                .isClosed(restaurantHours.getIsClosed())
                .build();
    }

    public static RestaurantHours toEntity(RestaurantHoursRequestDTO requestDTO) {
        return RestaurantHours.builder()
                .dayOfWeek(requestDTO.getDayOfWeek())
                .openingTime(requestDTO.getOpeningTime())
                .closingTime(requestDTO.getClosingTime())
                .isClosed(requestDTO.getIsClosed())
                .build();
    }
}
