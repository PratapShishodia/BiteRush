package com.biterush.notification_service.model.entity;

import com.biterush.notification_service.model.enums.TYPE;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notification")
public class Notifications {
    @Id
    private UUID notificationId;
    private UUID userId;
    private TYPE type;
    private String message;
    private LocalDateTime sentAt;
}
