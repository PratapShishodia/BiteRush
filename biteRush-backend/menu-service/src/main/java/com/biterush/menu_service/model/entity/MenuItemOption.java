package com.biterush.menu_service.model.entity;

import com.biterush.menu_service.model.enums.TYPE;
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
public class MenuItemOption {
    @Id
    private UUID menuItemOptionId;
    private UUID menuItemId;
    private String menuItemOptionName;
    private TYPE menuItemType;
    private Boolean isRequired;
    private Integer minSelection;
    private Integer maxSelection;
}
