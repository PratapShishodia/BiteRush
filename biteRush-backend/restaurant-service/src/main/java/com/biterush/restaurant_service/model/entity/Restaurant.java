package com.biterush.restaurant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Restaurant {
    @Id
    private UUID restaurantId;
    private UUID ownerId;
    private String restaurantName;
    private String slug;
    private String description;
    private String phone;
    private String email;
    private String logoUrl;
    private String coverImageUrl;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal rating;
    private Long totalRatings;
    private Integer priceForTwo;
    private Integer deliveryTimeMin;
    private Integer deliveryTimeMax;
    private Boolean isPureVeg;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RestaurantHours> restaurantHours;
    @ManyToMany
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id")
    )
    private Set<Cuisine> cuisines = new HashSet<>();
}
