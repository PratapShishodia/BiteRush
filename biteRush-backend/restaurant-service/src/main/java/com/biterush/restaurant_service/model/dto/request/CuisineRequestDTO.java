package com.biterush.restaurant_service.model.dto.request;

import lombok.*;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CuisineRequestDTO {
    private String name;
    private String slug;
    private Boolean isActive;
}
