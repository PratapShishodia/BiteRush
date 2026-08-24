package com.biterush.menu_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuItemOptionValue {
    @Id
    private UUID Id;
    private UUID menuItemOptionId;
    private String menuItemOptionValueName;
    private BigDecimal additionalPrice;
    private Boolean isAvailable;
}
