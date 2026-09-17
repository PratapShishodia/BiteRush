package com.biterush.notification_service.model.mapper;

import com.biterush.notification_service.model.dto.request.NotificationRequestDTO;
import com.biterush.notification_service.model.dto.response.NotificationResponseDTO;
import com.biterush.notification_service.model.entity.Notifications;

public class NotificationDTOMapper {
    public static Notifications toEntity(NotificationRequestDTO notificationRequestDTO) {
        return Notifications.builder()
                .userId(notificationRequestDTO.getUserId())
                .type(notificationRequestDTO.getType())
                .message(notificationRequestDTO.getMessage())
                .build();
    }

    public static NotificationResponseDTO toDTO(Notifications notifications) {
        return NotificationResponseDTO.builder()
                .notificationId(notifications.getNotificationId())
                .userId(notifications.getUserId())
                .type(notifications.getType())
                .message(notifications.getMessage())
                .sentAt(notifications.getSentAt())
                .build();
    }
}
