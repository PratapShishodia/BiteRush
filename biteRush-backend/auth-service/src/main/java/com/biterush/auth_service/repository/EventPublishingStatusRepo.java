package com.biterush.auth_service.repository;

import com.biterush.auth_service.model.entity.EventPublishingStatus;
import com.biterush.auth_service.model.enums.EVENT_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventPublishingStatusRepo extends JpaRepository<EventPublishingStatus, UUID> {
    List<EventPublishingStatus> findTop100ByStatusAndNextRetryAtLessThanEqualOrderByCreatedAtAsc(
            EVENT_STATUS status,
            LocalDateTime now
    );

    List<EventPublishingStatus> findTop100ByStatusOrderByCreatedAtAsc(
            EVENT_STATUS status
    );
}
