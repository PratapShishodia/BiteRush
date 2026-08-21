package com.biterush.user_service.model.mapper;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.DTO.UserResponseDTO;
import com.biterush.user_service.model.entity.Users;

public class UserDTOMapper {
    public static UserResponseDTO toDTO(Users user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profileImageUrl(user.getProfileImageUrl())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static Users toEntity(UserCreatedEvent dto) {
        return Users.builder()
                .userId(dto.getUserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .status(dto.getStatus())
                .build();
    }
}
