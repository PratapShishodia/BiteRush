package com.biterush.user_service.kafka;

import com.biterush.common.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserKafkaListener {

    private final UserConsumer userConsumer;

    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 2000, multiplier = 2.0),
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(topics = "user-topic", groupId = "user-service-group")
    public void consume(UserCreatedEvent userCreatedEvent){
        log.info("Received UserCreatedEvent: {}", userCreatedEvent);
        userConsumer.consumeUserEvent(userCreatedEvent);
    }

    @DltHandler
    public void handleDlt(UserCreatedEvent userCreatedEvent, ConsumerRecord<?,?> record) {
        log.error("Message sent to DLT:\n EventId: {}\n Event: {}", userCreatedEvent.getUserId(), userCreatedEvent);
    }

}
