package com.biterush.restaurant_service.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuisineRequestDTO {
    private String cuisineName;
    private String slug;
    private String imageUrl;
    private Boolean isActive;
}