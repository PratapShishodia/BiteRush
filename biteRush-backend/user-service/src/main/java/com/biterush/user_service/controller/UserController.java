package com.biterush.user_service.controller;

import com.biterush.user_service.model.DTO.UserAddressRequestDTO;
import com.biterush.user_service.model.DTO.UserAddressResponseDTO;
import com.biterush.user_service.model.DTO.UserResponseDTO;
import com.biterush.user_service.model.DTO.UserUpdateRequestDTO;
import com.biterush.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@RequestParam UUID userId) {
        return ResponseEntity.ok(userService.getMe(userId));
    }

    @PutMapping("/update/me")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam UUID userId, @RequestBody UserUpdateRequestDTO dto) {
        return ResponseEntity.ok(userService.update(userId, dto));
    }

    @PutMapping("/uploadImage/{userId}")
    public ResponseEntity<UserResponseDTO> uploadImage(@PathVariable UUID userId, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(userService.updateProfilePic(userId, file));
    }

    @PostMapping("/me/addresses")
    public ResponseEntity<UserAddressResponseDTO> createAddress(@RequestBody UserAddressRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAddress(dto));
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<UserAddressResponseDTO> updateAddress(@PathVariable Long addressId, @RequestBody UserAddressRequestDTO dto) {
        return ResponseEntity.ok(userService.updateAddress(addressId,dto));
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(userService.deleteAddress(addressId));
    }

    @PatchMapping("/addresses/{addressId}/default")
    public ResponseEntity<Boolean> setDefaultAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(userService.setDefaultAddress(addressId));
    }

    @GetMapping("/me/addresses")
    public ResponseEntity<List<UserAddressResponseDTO>> getAddress(@RequestParam UUID userId) {
        return ResponseEntity.ok(userService.getAllAddress(userId));
    }
}
