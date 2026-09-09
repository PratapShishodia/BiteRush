package com.biterush.favourite_service.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "favourites")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favourite {
    private String id;
    private UUID userId;
    private UUID restaurantId;
    private LocalDateTime createdAt;
}
