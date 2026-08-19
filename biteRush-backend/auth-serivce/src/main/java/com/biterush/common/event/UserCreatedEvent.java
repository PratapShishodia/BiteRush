package com.biterush.common.event;

import com.biterush.auth_serivce.model.enums.STATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatedEvent {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private UUID activationToken;
    private LocalDateTime activationTokenExpiry;
    private STATUS status;
}
