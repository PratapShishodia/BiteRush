package com.biterush.notification_service.repository;

import com.biterush.notification_service.model.entity.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepo extends MongoRepository<Notifications, UUID> {
    List<Notifications> findByUserId(UUID userId);
}
