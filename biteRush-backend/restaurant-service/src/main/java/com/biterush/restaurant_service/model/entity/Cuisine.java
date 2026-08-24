package com.biterush.restaurant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cuisineId;
    private String cuisineName;
    private String slug;
    private String imageUrl;
    private Boolean isActive;
    @ManyToMany(mappedBy = "cuisines")
    private Set<Restaurant> restaurants = new HashSet<>();
}
