package com.biterush.auth_service.model.mapper;

import com.biterush.auth_service.model.dto.request.RefreshTokenRequestDTO;
import com.biterush.auth_service.model.dto.response.RefreshTokenResponseDTO;
import com.biterush.auth_service.model.entity.RefreshToken;

public class RefreshTokenDTOMapper {

    public static RefreshTokenResponseDTO toDTO(RefreshToken refreshToken) {
        return RefreshTokenResponseDTO.builder()
                .refreshTokenId(refreshToken.getRefreshTokenId())
                .userId(refreshToken.getUserId())
                .refreshToken(refreshToken.getRefreshToken())
                .expiresAt(refreshToken.getExpiresAt())
                .issuedAt(refreshToken.getIssuedAt())
                .status(refreshToken.getStatus())
                .build();
    }

    public static RefreshToken toEntity(RefreshTokenRequestDTO requestDTO) {
        return RefreshToken.builder()
                .userId(requestDTO.getUserId())
                .refreshToken(requestDTO.getRefreshToken())
                .build();
    }
}
