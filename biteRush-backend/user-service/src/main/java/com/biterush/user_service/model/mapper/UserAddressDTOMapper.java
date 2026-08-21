package com.biterush.user_service.model.mapper;

import com.biterush.user_service.model.DTO.UserAddressRequestDTO;
import com.biterush.user_service.model.DTO.UserAddressResponseDTO;
import com.biterush.user_service.model.entity.UserAddress;

public class UserAddressDTOMapper {

    public static UserAddress toEntity(UserAddressRequestDTO requestDTO){
        return UserAddress.builder()
                .label(requestDTO.getLabel())
                .addressLine1(requestDTO.getAddressLine1())
                .addressLine2(requestDTO.getAddressLine2())
                .landmark(requestDTO.getLandmark())
                .city(requestDTO.getCity())
                .state(requestDTO.getState())
                .country(requestDTO.getCountry())
                .postalCode(requestDTO.getPostalCode())
                .latitude(requestDTO.getLatitude())
                .longitude(requestDTO.getLongitude())
                .build();
    }

    public static UserAddressResponseDTO toDTO(UserAddress entity){
        return UserAddressResponseDTO.builder()
                .addressId(entity.getAddressId())
                .label(entity.getLabel())
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .landmark(entity.getLandmark())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .isDefault(entity.isDefault())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
