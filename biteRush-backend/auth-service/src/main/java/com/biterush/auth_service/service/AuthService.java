package com.biterush.auth_service.service;

import com.biterush.auth_service.model.dto.common.LoginRequestDTO;
import com.biterush.auth_service.model.dto.common.LoginResponseDTO;
import com.biterush.auth_service.model.dto.common.PasswordChangeRequest;
import com.biterush.auth_service.model.dto.common.RefreshTokenRequest;
import com.biterush.auth_service.model.dto.request.UserCredentialsRequestDTO;
import com.biterush.auth_service.model.dto.response.UserCredentialsResponseDTO;

import java.util.UUID;

public interface AuthService {
    UserCredentialsResponseDTO registerUser(UserCredentialsRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    LoginResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest);
    Boolean logout(UUID userId);
    Boolean activateProfile(String token);
    Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest);
    Boolean sendOTP(String email);
    Boolean changePassword(PasswordChangeRequest passwordChangeRequest);
    Boolean changeEmail(UUID userId, String newEmail);
    UserCredentialsResponseDTO getLoggedInUser();
    Boolean verifyOTP(String email, String OTP);
}
