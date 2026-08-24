package com.biterush.menu_service.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemOptionValueRequestDTO {
    private UUID menuItemOptionId;
    private String menuItemOptionValueName;
    private BigDecimal additionalPrice;
    private Boolean isAvailable;
}
