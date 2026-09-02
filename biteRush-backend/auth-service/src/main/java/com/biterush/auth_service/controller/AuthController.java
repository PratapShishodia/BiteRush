package com.biterush.auth_service.controller;

import com.biterush.auth_service.model.dto.common.LoginRequestDTO;
import com.biterush.auth_service.model.dto.common.LoginResponseDTO;
import com.biterush.auth_service.model.dto.common.PasswordChangeRequest;
import com.biterush.auth_service.model.dto.common.RefreshTokenRequest;
import com.biterush.auth_service.model.dto.request.UserCredentialsRequestDTO;
import com.biterush.auth_service.model.dto.response.UserCredentialsResponseDTO;
import com.biterush.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserCredentialsResponseDTO> signUp(UserCredentialsRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(requestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO){
        return ResponseEntity.ok(authService.login(requestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(RefreshTokenRequest requestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(requestDTO));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestParam UUID userId){
        return ResponseEntity.ok(authService.logout(userId));
    }

    @PatchMapping("/activate/{token}")
    public ResponseEntity<Boolean> activateProfile(@PathVariable String token){
        return ResponseEntity.ok(authService.activateProfile(token));
    }

    @PutMapping("/forget-password")
    public ResponseEntity<Boolean> forgetPassword(@RequestParam String email, @RequestParam String OTP, @RequestBody PasswordChangeRequest request){
        return ResponseEntity.ok(authService.forgetPassword(email, OTP, request));
    }

    @PutMapping("/send-otp")
    public ResponseEntity<Boolean> sendOtp(@RequestParam String email){
        return ResponseEntity.ok(authService.sendOTP(email));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody PasswordChangeRequest request){
        return ResponseEntity.ok(authService.changePassword(request));
    }

    @PutMapping("/change-email")
    public ResponseEntity<Boolean> changeEmail(@RequestParam UUID userId,@RequestParam String email){
        return ResponseEntity.ok(authService.changeEmail(userId,email));
    }

    @GetMapping("/me")
    public ResponseEntity<UserCredentialsResponseDTO> getCurrentUser(){
        return ResponseEntity.ok(authService.getLoggedInUser());
    }

    @PutMapping("/verify-otp")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam String email,@RequestParam String otp){
        return ResponseEntity.ok(authService.verifyOTP(email,otp));
    }

}
