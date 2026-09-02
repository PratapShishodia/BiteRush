package com.biterush.user_service.service;

import com.biterush.common.event.UserCreatedEvent;
import com.biterush.user_service.model.dto.request.AddressRequestDTO;
import com.biterush.user_service.model.dto.request.UserRequestDTO;
import com.biterush.user_service.model.dto.response.AddressResponseDTO;
import com.biterush.user_service.model.dto.response.UserResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO getMe(UUID userId);
    UserResponseDTO update(UUID userId, UserRequestDTO dto);
    UserResponseDTO updateProfilePic(UUID userId, MultipartFile file);
    UserResponseDTO createProfile(UserCreatedEvent userCreatedEvent);
    AddressResponseDTO createAddress(AddressRequestDTO dto);
    List<AddressResponseDTO> getAllAddress(UUID userId);
    AddressResponseDTO updateAddress(UUID addressId, AddressRequestDTO dto);
    Boolean deleteAddress(UUID addressId);
    Boolean setDefaultAddress(UUID addressId);
}
