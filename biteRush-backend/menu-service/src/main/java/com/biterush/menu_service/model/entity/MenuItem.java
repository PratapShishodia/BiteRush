package com.biterush.menu_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemId;
    private UUID restaurantId;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private String itemPrice;
    private String discountedPrice;
    private Boolean isVeg;
    private Boolean isAvailable;
    private Integer preparationTime;
    private String ItemImgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MenuCategory category;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isAvailable = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
