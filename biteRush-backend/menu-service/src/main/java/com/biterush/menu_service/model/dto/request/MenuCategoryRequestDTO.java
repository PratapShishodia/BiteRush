package com.biterush.menu_service.model.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuCategoryRequestDTO {
    private UUID restaurantId;
    private String categoryName;
    private String categoryDescription;
    private Integer displayOrder;
    private Boolean isActive;
}
