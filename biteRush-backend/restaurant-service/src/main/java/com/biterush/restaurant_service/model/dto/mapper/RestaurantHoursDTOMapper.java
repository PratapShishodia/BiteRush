package com.biterush.restaurant_service.model.dto.mapper;

import com.biterush.restaurant_service.model.dto.request.RestaurantHoursRequestDTO;
import com.biterush.restaurant_service.model.entity.RestaurantHours;

public class RestaurantHoursDTOMapper {

    public static RestaurantHours toEntity(RestaurantHoursRequestDTO dto) {
        return RestaurantHours.builder()
                .dayOfWeek(dto.getDayOfWeek())
                .openingTime(dto.getOpeningTime())
                .closingTime(dto.getClosingTime())
                .isClosed(dto.getIsClosed())
                .build();
    }

}
