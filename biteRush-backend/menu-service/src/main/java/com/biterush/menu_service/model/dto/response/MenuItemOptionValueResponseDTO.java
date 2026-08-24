package com.biterush.menu_service.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemOptionValueResponseDTO {
    private UUID Id;
    private UUID menuItemOptionId;
    private String menuItemOptionValueName;
    private BigDecimal additionalPrice;
    private Boolean isAvailable;
}
