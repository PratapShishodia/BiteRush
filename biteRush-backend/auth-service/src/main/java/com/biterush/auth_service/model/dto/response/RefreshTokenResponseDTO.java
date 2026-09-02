package com.biterush.auth_service.model.dto.response;

import com.biterush.auth_service.model.enums.STATUS;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenResponseDTO {
    private UUID refreshTokenId;
    private UUID userId;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    private STATUS status;
}
