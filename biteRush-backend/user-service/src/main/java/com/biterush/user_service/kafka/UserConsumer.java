package com.biterush.user_service.kafka;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

    private final UserService userService;

    @KafkaListener(topics = "user-topic",groupId = "user-group")
    public void consume(UserCreatedEvent event) {
        try {
            log.info("Received User Created notification for user {}", event.getUserId());
            userService.createProfile(event);
            log.info("User Created Successfully");
        } catch (Exception ex) {
            log.error("Failed to process User Created event for user {}", event.getUserId(), ex);
            throw ex;
        }
    }

}
