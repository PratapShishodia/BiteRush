package com.biterush.favourite_service.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteResponseDTO {
    private String id;
    private UUID userId;
    private UUID restaurantId;
    private LocalDateTime createdAt;
}
