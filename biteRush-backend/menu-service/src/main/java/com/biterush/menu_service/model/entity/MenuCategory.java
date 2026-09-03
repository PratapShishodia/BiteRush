package com.biterush.menu_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryIconUrl;
    private UUID restaurantId;
    private Boolean isActive;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<MenuItem> menuItemList;
    @PrePersist
    public void prePersist() {
        this.isActive = true;
    }
}
