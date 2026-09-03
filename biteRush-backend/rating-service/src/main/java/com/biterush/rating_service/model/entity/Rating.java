package com.biterush.rating_service.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "reviews")
public class Rating {
    @Id
    private String id;
    private UUID usernameId;
    private String username;
    private UUID restaurantId;
    private BigDecimal rating;
    private String reviewText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}