package com.biterush.menu_service.model.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuCategoryRequestDTO {
    private String categoryName;
    private String categoryDescription;
    private UUID restaurantId;
    private Boolean isActive;
}
