package com.biterush.favourite_service.model.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteRequestDTO {
    private UUID userId;
    private UUID restaurantId;
    private LocalDateTime createdAt;
}
