package com.biterush.menu_service.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequestDTO {
    private UUID restaurantId;
    private UUID categoryId;
    private String menuItemName;
    private String menuItemDescription;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private Boolean isVeg;
    private Boolean isAvailable;
    private Integer preparationTime;
    private Integer displayOrder;
}
