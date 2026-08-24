package com.biterush.menu_service.model.dto.request;

import com.biterush.menu_service.model.enums.TYPE;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemOptionRequestDTO {
    private UUID menuItemId;
    private String menuItemOptionName;
    private TYPE menuItemType;
    private Boolean isRequired;
    private Integer minSelection;
    private Integer maxSelection;
}
