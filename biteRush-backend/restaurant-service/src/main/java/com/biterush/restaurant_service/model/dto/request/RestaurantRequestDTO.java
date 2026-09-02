package com.biterush.restaurant_service.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequestDTO {
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal rating;
    private Integer ratingCount;
    private Integer priceForTwo;
    private Integer deliveryMinTime;
    private Integer deliveryMaxTime;
    private Boolean isPureVeg;
    private Boolean isActive;
    private List<UUID> cuisineIds;
}
