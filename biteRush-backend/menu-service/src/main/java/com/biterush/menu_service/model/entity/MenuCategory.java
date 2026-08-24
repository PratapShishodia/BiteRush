package com.biterush.menu_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuCategory {
    @Id
    private UUID categoryId;
    private UUID restaurantId;
    private String categoryName;
    private String categoryDescription;
    private Integer displayOrder;
    private Boolean isActive;
}
