package com.biterush.auth_serivce.service;

import com.biterush.auth_serivce.model.dto.PasswordChangeRequest;
import com.biterush.auth_serivce.model.dto.RefreshTokenRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsRequest;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;
import com.biterush.auth_serivce.model.dto.common.LoginRequestDTO;
import com.biterush.auth_serivce.model.dto.common.LoginResponseDTO;

import java.util.UUID;

public interface AuthService {
    UserCredentialsResponse registerUser(UserCredentialsRequest request);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    LoginResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest);
    Boolean logout(UUID userId);
    Boolean activateProfile(String token);
    Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest);
    Boolean sendOTP(String email);
    Boolean changePassword(PasswordChangeRequest passwordChangeRequest);
    Boolean changeEmail(UUID userId, String newEmail);
    UserCredentialsResponse getLoggedInUser();
    Boolean verifyOTP(String email, String OTP);
}
