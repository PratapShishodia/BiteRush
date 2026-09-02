package com.biterush.common.event;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class NotificationEvent {
    private UUID userId;
    private String recipient;
    private String phone;
    private String message;
    private String subject;
}
