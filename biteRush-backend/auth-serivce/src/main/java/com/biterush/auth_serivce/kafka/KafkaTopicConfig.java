package com.biterush.auth_serivce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topics.user}")
    private String userTopic;
    @Value("${spring.kafka.topics.notification}")
    private String notificationTopic;

    private NewTopic userTopic() {
        return TopicBuilder.name(userTopic).partitions(3).replicas(1).build();
    }

    private NewTopic notificationTopic() {
        return TopicBuilder.name(notificationTopic).partitions(3).replicas(1).build();
    }
}
