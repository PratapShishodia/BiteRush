package com.biterush.notification_service.model.dto.response;

import com.biterush.notification_service.model.enums.TYPE;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponseDTO {
    private UUID notificationId;
    private UUID userId;
    private TYPE type;
    private String message;
    private LocalDateTime sentAt;
}
