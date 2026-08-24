package com.biterush.restaurant_service.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDTO {
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
    private Integer priceForTwo;
    private Integer deliveryTimeMin;
    private Integer deliveryTimeMax;
    private Boolean isPureVeg;
    private Set<Long> cuisineIds;
}