package com.biterush.notification_service.service;

import com.biterush.notification_service.model.dto.request.NotificationRequestDTO;
import com.biterush.notification_service.model.dto.response.NotificationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    String sendNotification(NotificationRequestDTO requestDTO);
    List<NotificationResponseDTO> getNotifications();
    List<NotificationResponseDTO> getNotificationsByUserId(UUID userId);
}
