package com.biterush.auth_serivce.model.dto;

import com.biterush.auth_serivce.model.enums.STATUS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsResponse {

    private Long credentialID;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private STATUS status;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
