package com.biterush.menu_service.model.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequestDTO {
    private UUID restaurantId;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private String itemPrice;
    private String discountedPrice;
    private Boolean isVeg;
    private Boolean isAvailable;
    private Integer preparationTime;
    private UUID categoryId;
}
