package com.biterush.auth_service.kafka;

import com.biterush.auth_service.model.entity.EventPublishingStatus;
import com.biterush.auth_service.model.enums.EVENT_STATUS;
import com.biterush.auth_service.repository.EventPublishingStatusRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {
    private static final int MAX_RETRIES = 10;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventPublishingStatusRepo eventPublishingStatusRepo;

    @Scheduled(fixedRate = 1000)
    public void publishEvent() {
        List<EventPublishingStatus> eventPublishingStatuses = eventPublishingStatusRepo.findTop100ByStatusOrderByCreatedAtAsc(EVENT_STATUS.PENDING);
        for (EventPublishingStatus eventPublishingStatus : eventPublishingStatuses) {
            publish(eventPublishingStatus);
        }
    }

    private void publish(EventPublishingStatus event) {
        try{
            kafkaTemplate.send(event.getTopic(),event.getProducerId().toString(), event.getPayload()).get();
            event.setStatus(EVENT_STATUS.PUBLISHED);
            event.setPublishedAt(LocalDateTime.now());
            eventPublishingStatusRepo.save(event);
        }
        catch (Exception e){
            int retryCount = event.getRetryCount() + 1;

            event.setRetryCount(retryCount);
            event.setLastError(e.getMessage());

            if (retryCount >= MAX_RETRIES) {
                // No more automatic retries
                event.setStatus(EVENT_STATUS.FAILED);
            } else {
                // Retry later
                event.setStatus(EVENT_STATUS.PENDING);
                event.setNextRetryAt(
                        LocalDateTime.now().plusSeconds(calculateRetryDelay(retryCount))
                );
            }
        }
        eventPublishingStatusRepo.save(event);
        }

    private long calculateRetryDelay(int retryCount) {
        return Math.min(60,(long) Math.pow(2, retryCount));
    }

}
