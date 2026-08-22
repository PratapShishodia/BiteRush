package com.biterush.auth_serivce.model.mapper;

import com.biterush.auth_serivce.model.dto.RefreshTokenRequest;
import com.biterush.auth_serivce.model.dto.RefreshTokenResponse;
import com.biterush.auth_serivce.model.entity.RefreshToken;

public class RefreshDTOMapper {

    public static RefreshTokenResponse toDTO(RefreshToken token){
        return RefreshTokenResponse.builder()
                .refreshTokenId(token.getRefreshTokenId())
                .userId(token.getUserId())
                .refreshToken(token.getRefreshToken())
                .expiresAt(token.getExpiresAt())
                .revokedAt(token.getRevokedAt())
                .createdAt(token.getCreatedAt())
                .build();
    }

    public static RefreshToken toEntity(RefreshTokenRequest request){
        return RefreshToken.builder()
                .refreshToken(request.getRefreshToken())
                .build();
    }
}
