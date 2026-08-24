package com.biterush.menu_service.model.dto.response;

import com.biterush.menu_service.model.enums.TYPE;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemOptionResponseDTO {
    private UUID menuItemOptionId;
    private UUID menuItemId;
    private String menuItemOptionName;
    private TYPE menuItemType;
    private Boolean isRequired;
    private Integer minSelection;
    private Integer maxSelection;
}
