package com.biterush.user_service.repository;

import com.biterush.user_service.model.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessedEventRepo extends JpaRepository<ProcessedEvent, UUID> {
    Boolean existsByEventId(UUID eventId);
}
