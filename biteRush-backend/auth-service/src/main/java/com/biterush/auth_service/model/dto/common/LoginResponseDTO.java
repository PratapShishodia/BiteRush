package com.biterush.auth_service.model.dto.common;

import com.biterush.auth_service.model.dto.response.UserCredentialsResponseDTO;
import com.biterush.auth_service.model.entity.UserCredentials;

public record LoginResponseDTO(UserCredentialsResponseDTO userCredentials, String accessToken, String refreshToken) {
}
