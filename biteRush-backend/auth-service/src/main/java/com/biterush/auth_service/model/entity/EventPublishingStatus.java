package com.biterush.auth_service.model.entity;

import com.biterush.auth_service.model.enums.EVENT_STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@Entity
public class EventPublishingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String producer;
    private UUID producerId;
    private String eventType;
    private String topic;
    @Lob
    @Column(name = "payload", nullable = false, columnDefinition = "TEXT")
    private String payload;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EVENT_STATUS status = EVENT_STATUS.PENDING;
    @Builder.Default
    private int retryCount = 0;
    private String lastError;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime publishedAt;
    private LocalDateTime nextRetryAt;
}
