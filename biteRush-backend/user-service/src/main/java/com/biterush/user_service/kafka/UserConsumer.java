package com.biterush.user_service.kafka;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.entity.ProcessedEvent;
import com.biterush.user_service.repository.ProcessedEventRepo;
import com.biterush.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final ProcessedEventRepo processedEventRepo;
    private final UserService userService;

    @Transactional
    public void consumeUserEvent(UserCreatedEvent userEvent) {
        if(processedEventRepo.existsByEventId(userEvent.getUserId())) {
            log.info("Event with ID {} has already been processed. Skipping.", userEvent.getUserId());
            return;
        }

        userService.createProfile(userEvent);

        processedEventRepo.save(ProcessedEvent.builder()
                        .eventId(userEvent.getUserId())
                        .processedAt(LocalDateTime.now())
                .build());
    }
}
