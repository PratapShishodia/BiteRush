package com.biterush.user_service.controller;

import com.biterush.user_service.model.dto.request.AddressRequestDTO;
import com.biterush.user_service.model.dto.request.UserRequestDTO;
import com.biterush.user_service.model.dto.response.AddressResponseDTO;
import com.biterush.user_service.model.dto.response.UserResponseDTO;
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
    public ResponseEntity<UserResponseDTO> updateUser(@RequestParam UUID userId, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.update(userId, dto));
    }

    @PutMapping("/uploadImage/{userId}")
    public ResponseEntity<UserResponseDTO> uploadImage(@PathVariable UUID userId, @RequestParam MultipartFile file) {
        return ResponseEntity.ok(userService.updateProfilePic(userId, file));
    }

    @PostMapping("/me/addresses")
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAddress(dto));
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable UUID addressId, @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.ok(userService.updateAddress(addressId,dto));
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable UUID addressId) {
        return ResponseEntity.ok(userService.deleteAddress(addressId));
    }

    @PatchMapping("/addresses/{addressId}/default")
    public ResponseEntity<Boolean> setDefaultAddress(@PathVariable UUID addressId) {
        return ResponseEntity.ok(userService.setDefaultAddress(addressId));
    }

    @GetMapping("/me/addresses")
    public ResponseEntity<List<AddressResponseDTO>> getAddress(@RequestParam UUID userId) {
        return ResponseEntity.ok(userService.getAllAddress(userId));
    }
}
