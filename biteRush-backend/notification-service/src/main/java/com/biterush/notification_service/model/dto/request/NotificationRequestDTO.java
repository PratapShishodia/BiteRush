package com.biterush.notification_service.model.dto.request;

import com.biterush.notification_service.model.enums.TYPE;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDTO {
    private UUID userId;
    private TYPE type;
    private String message;
}
