package com.biterush.notification_service.service.impl;

import com.biterush.notification_service.model.dto.request.NotificationRequestDTO;
import com.biterush.notification_service.model.dto.response.NotificationResponseDTO;
import com.biterush.notification_service.model.entity.Notifications;
import com.biterush.notification_service.model.mapper.NotificationDTOMapper;
import com.biterush.notification_service.repository.NotificationRepo;
import com.biterush.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;

    @Override
    public String sendNotification(NotificationRequestDTO requestDTO) {
        Notifications notifications = NotificationDTOMapper.toEntity(requestDTO);
        notifications.setSentAt(LocalDateTime.now());
        notifications.setNotificationId(UUID.randomUUID());

        //add sent functionality

        notificationRepo.save(notifications);
        return "Notification has been sent";
    }

    @Override
    public List<NotificationResponseDTO> getNotifications() {
        List<Notifications> notifications = notificationRepo.findAll();
        return notifications.stream().map(NotificationDTOMapper::toDTO).toList();
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(UUID userId) {
        List<Notifications> notifications = notificationRepo.findByUserId(userId);
        return notifications.stream().map(NotificationDTOMapper::toDTO).toList();
    }
}
