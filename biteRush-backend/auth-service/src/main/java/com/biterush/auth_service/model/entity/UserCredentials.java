package com.biterush.auth_service.model.entity;

import com.biterush.auth_service.model.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID credentialsId;
    private UUID userId;
    private String email;
    private String phone;
    private String password;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    private String activationToken;
    private String OTP;
    private LocalDateTime OTPExpiry;
    private Boolean OTPVerified;
    private LocalDateTime activationTokenExpiry;
    private List<String> roles;
    @PrePersist
    protected void onCreate() {
        this.status = STATUS.INACTIVE;
        this.userId = UUID.randomUUID();
        this.activationToken = UUID.randomUUID().toString();
        this.activationTokenExpiry = LocalDateTime.now().plusHours(2);
        this.emailVerified = false;
        this.phoneVerified = false;
        this.OTPVerified = false;
        this.roles = List.of("USER");
    }
}
