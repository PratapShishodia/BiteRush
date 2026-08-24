package com.biterush.menu_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuItem {
    @Id
    private UUID menuItemId;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
