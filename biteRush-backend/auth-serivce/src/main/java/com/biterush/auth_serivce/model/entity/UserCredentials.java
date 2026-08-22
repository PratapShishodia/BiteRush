package com.biterush.auth_serivce.model.entity;

import com.biterush.auth_serivce.model.enums.STATUS;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long credentialID;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHashed;
    private String OTP;
    private boolean isOTPVerified;
    private LocalDateTime otpExpiry;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private String activationToken;
    private LocalDateTime activationTokenExpiry;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
