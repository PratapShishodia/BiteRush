package com.biterush.auth_serivce.model.mapper;

import com.biterush.auth_serivce.model.dto.UserCredentialsRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;
import com.biterush.auth_serivce.model.entity.UserCredentials;

public class UserCredentialsDTOMapper {
    public static UserCredentialsResponse toDTO(UserCredentials userCredentials){
        return UserCredentialsResponse.builder()
                .firstName(userCredentials.getFirstName())
                .lastName(userCredentials.getLastName())
                .credentialID(userCredentials.getCredentialID())
                .userId(userCredentials.getUserId())
                .email(userCredentials.getEmail())
                .phone(userCredentials.getPhone())
                .emailVerified(false)
                .phoneVerified(false)
                .status(userCredentials.getStatus())
                .build();
    }

    public static UserCredentials toEntity(UserCredentialsRequest request){
        return UserCredentials.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .emailVerified(request.getEmailVerified())
                .phoneVerified(request.getPhoneVerified())
                .build();
    }
}
