package com.biterush.auth_service.model.mapper;

import com.biterush.auth_service.model.dto.request.UserCredentialsRequestDTO;
import com.biterush.auth_service.model.dto.response.UserCredentialsResponseDTO;
import com.biterush.auth_service.model.entity.UserCredentials;

public class UserCredentialsDTOMapper {

    public static UserCredentialsResponseDTO toDTO(UserCredentials userCredentials) {
        return UserCredentialsResponseDTO.builder()
                .credentialsId(userCredentials.getCredentialsId())
                .userId(userCredentials.getUserId())
                .email(userCredentials.getEmail())
                .phone(userCredentials.getPhone())
                .password(userCredentials.getPassword())
                .emailVerified(userCredentials.getEmailVerified())
                .phoneVerified(userCredentials.getPhoneVerified())
                .status(userCredentials.getStatus())
                .build();
    }

    public static UserCredentials toEntity(UserCredentialsRequestDTO requestDTO) {
        return UserCredentials.builder()
                .userId(requestDTO.getUserId())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .password(requestDTO.getPassword())
                .build();
    }

}
