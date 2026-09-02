package com.biterush.auth_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class TopicConfig {

    @Value("${spring.kafka.topic.user}")
    private String userTopic;
    @Value("${spring.kafka.topic.notification}")
    private String notificationTopic;

    
    private NewTopic createUserTopic(){
        return TopicBuilder.name(userTopic).replicas(3).partitions(1).build();
    }

    private NewTopic createNotificationTopic(){
        return TopicBuilder.name(notificationTopic).replicas(3).partitions(1).build();
    }
}