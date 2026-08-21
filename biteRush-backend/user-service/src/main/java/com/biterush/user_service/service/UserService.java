package com.biterush.user_service.service;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.DTO.UserAddressRequestDTO;
import com.biterush.user_service.model.DTO.UserAddressResponseDTO;
import com.biterush.user_service.model.DTO.UserResponseDTO;
import com.biterush.user_service.model.DTO.UserUpdateRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO getMe(UUID userId);
    UserResponseDTO update(UUID userId, UserUpdateRequestDTO dto);
    UserResponseDTO updateProfilePic(UUID userId, MultipartFile file);
    UserResponseDTO createProfile(UserCreatedEvent userCreatedEvent);
    UserAddressResponseDTO createAddress(UserAddressRequestDTO dto);
    List<UserAddressResponseDTO> getAllAddress(UUID userId);
    UserAddressResponseDTO updateAddress(Long addressId, UserAddressRequestDTO dto);
    Boolean deleteAddress(Long addressId);
    Boolean setDefaultAddress(Long addressId);
}
