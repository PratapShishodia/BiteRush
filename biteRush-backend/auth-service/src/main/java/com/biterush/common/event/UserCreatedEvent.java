package com.biterush.common.event;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatedEvent {
    private UUID userId;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
}
