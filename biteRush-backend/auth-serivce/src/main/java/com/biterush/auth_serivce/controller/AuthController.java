package com.biterush.auth_serivce.controller;

import com.biterush.auth_serivce.model.dto.PasswordChangeRequest;
import com.biterush.auth_serivce.model.dto.RefreshTokenRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;
import com.biterush.auth_serivce.model.dto.common.LoginRequestDTO;
import com.biterush.auth_serivce.model.dto.common.LoginResponseDTO;
import com.biterush.auth_serivce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserCredentialsResponse> register(@RequestBody UserCredentialsRequest userCredentials) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(userCredentials));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestParam UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.logout(userId));
    }

    @PostMapping("/activate-profile/{activationToken}")
    public ResponseEntity<Boolean> activateProfile(@PathVariable String activationToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.activateProfile(activationToken));
    }

    @PostMapping("/forget-password")
    public ResponseEntity<Boolean> forgetPassword(@RequestParam String email,@RequestParam String OTP,@RequestBody PasswordChangeRequest newPassword) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.forgetPassword(email, OTP, newPassword));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<Boolean> sendOtp(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.sendOTP(email));
    }

    @PutMapping("/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.changePassword(passwordChangeRequest));
    }

    @PutMapping("/email")
    public ResponseEntity<Boolean> changeEmail(@RequestParam UUID userId,@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.changeEmail(userId, email));
    }

    @GetMapping("/me")
    public ResponseEntity<UserCredentialsResponse> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getLoggedInUser());
    }

    @PutMapping("/verify-otp")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam String email,@RequestParam String otp) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.verifyOTP(email,otp));
    }
}
