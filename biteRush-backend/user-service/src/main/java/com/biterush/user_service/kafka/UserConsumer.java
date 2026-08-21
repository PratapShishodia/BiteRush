package com.biterush.user_service.kafka;

import com.biterush.common.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

    @KafkaListener(topics = "user-topic",groupId = "user-group")
    public void consume(UserCreatedEvent event) {
        try {
//            log.info("Received payment notification for user {}", event.getUserId());
//            emailService.sendEmail(event.getRecipient(), event.getSubject(), event.getMessage());
//            log.info("Payment Email sent successfully to {}", event.getRecipient());
        } catch (Exception ex) {
            log.error("Failed to process payment notification for user {}", event.getUserId(), ex);
            throw ex;
        }
    }

}
