package com.biterush.user_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessedEvent {
    @Id
    private UUID eventId;
    private LocalDateTime processedAt;
}
