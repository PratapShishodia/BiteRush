package com.biterush.restaurant_service.model.dto.response;

import com.biterush.restaurant_service.model.enums.DayOfWeek;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantHoursResponseDTO {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Boolean isClosed;
}