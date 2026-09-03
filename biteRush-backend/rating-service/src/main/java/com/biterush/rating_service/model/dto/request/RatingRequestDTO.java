package com.biterush.rating_service.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingRequestDTO {
    private UUID usernameId;
    private String username;
    private UUID restaurantId;
    private BigDecimal rating;
    private String reviewText;
}
