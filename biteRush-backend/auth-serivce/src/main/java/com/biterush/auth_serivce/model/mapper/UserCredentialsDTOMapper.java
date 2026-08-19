package com.biterush.auth_serivce.model.mapper;

import com.biterush.auth_serivce.model.dto.UserCredentialsRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;
import com.biterush.auth_serivce.model.entity.UserCredentials;

public class UserCredentialsDTOMapper {
    public static UserCredentialsResponse toDTO(UserCredentials userCredentials){
        return UserCredentialsResponse.builder()
                .credentialID(userCredentials.getCredentialID())
                .userId(userCredentials.getUserId())
                .email(userCredentials.getEmail())
                .phone(userCredentials.getPhone())
                .emailVerified(userCredentials.getEmailVerified())
                .phoneVerified(userCredentials.getPhoneVerified())
                .status(userCredentials.getStatus())
                .lastLoginAt(userCredentials.getLastLoginAt())
                .createdAt(userCredentials.getCreatedAt())
                .updatedAt(userCredentials.getUpdatedAt())
                .build();
    }

    public static UserCredentials toEntity(UserCredentialsRequest request){
        return UserCredentials.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .emailVerified(request.getEmailVerified())
                .phoneVerified(request.getPhoneVerified())
                .build();
    }
}
