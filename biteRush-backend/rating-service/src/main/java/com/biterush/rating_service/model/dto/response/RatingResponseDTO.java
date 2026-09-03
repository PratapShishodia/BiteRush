package com.biterush.rating_service.model.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponseDTO {
    private String id;
    private UUID usernameId;
    private String username;
    private UUID restaurantId;
    private BigDecimal rating;
    private String reviewText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
