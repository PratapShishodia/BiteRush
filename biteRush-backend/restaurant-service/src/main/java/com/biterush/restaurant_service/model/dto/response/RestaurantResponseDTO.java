package com.biterush.restaurant_service.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDTO {
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
    private List<RestaurantHoursResponseDTO> restaurantHours;
    private List<CuisineResponseDTO> cuisines;
}