package com.biterush.restaurant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cuisineId;

    private String name;
    private String slug;
    private String imageUrl;
    @Builder.Default
    private Boolean isActive = true;
}