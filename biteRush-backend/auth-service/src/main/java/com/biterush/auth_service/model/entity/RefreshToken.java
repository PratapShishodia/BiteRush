package com.biterush.auth_service.model.entity;

import com.biterush.auth_service.model.enums.STATUS;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {
    @Id
    private UUID refreshTokenId;
    private UUID userId;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    private STATUS status;
    @PrePersist
    protected void onCreate() {
        this.issuedAt = LocalDateTime.now();
        this.status = STATUS.ACTIVE;
        this.expiresAt = LocalDateTime.now().plusDays(7);
    }
}
