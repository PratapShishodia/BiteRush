package com.biterush.restaurant_service.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuisineResponseDTO {
    private Long cuisineId;
    private String cuisineName;
    private String slug;
    private String imageUrl;
    private Boolean isActive;
}