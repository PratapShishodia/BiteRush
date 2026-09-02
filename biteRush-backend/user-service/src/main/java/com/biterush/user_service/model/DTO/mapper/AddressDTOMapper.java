package com.biterush.user_service.model.dto.mapper;

import com.biterush.user_service.model.dto.request.AddressRequestDTO;
import com.biterush.user_service.model.dto.response.AddressResponseDTO;
import com.biterush.user_service.model.entity.Address;

public class AddressDTOMapper {

    public static AddressResponseDTO toDTO(Address address) {
        return AddressResponseDTO.builder()
                .addressId(address.getAddressId())
                .addressType(address.getAddressType())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .landmark(address.getLandmark())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .isDefault(address.getIsDefault())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

    public static Address toEntity(AddressRequestDTO requestDTO) {
        return Address.builder()
                .addressType(requestDTO.getAddressType())
                .addressLine1(requestDTO.getAddressLine1())
                .addressLine2(requestDTO.getAddressLine2())
                .landmark(requestDTO.getLandmark())
                .city(requestDTO.getCity())
                .state(requestDTO.getState())
                .country(requestDTO.getCountry())
                .postalCode(requestDTO.getPostalCode())
                .latitude(requestDTO.getLatitude())
                .longitude(requestDTO.getLongitude())
                .isDefault(requestDTO.getIsDefault())
                .createdAt(requestDTO.getCreatedAt())
                .updatedAt(requestDTO.getUpdatedAt())
                .build();
    }

}
