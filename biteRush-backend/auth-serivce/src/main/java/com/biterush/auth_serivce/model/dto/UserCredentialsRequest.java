package com.biterush.auth_serivce.model.dto;

import com.biterush.auth_serivce.model.enums.STATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
