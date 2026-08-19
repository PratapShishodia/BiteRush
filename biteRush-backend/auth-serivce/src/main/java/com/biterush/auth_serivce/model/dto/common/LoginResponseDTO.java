package com.biterush.auth_serivce.model.dto.common;

import com.biterush.auth_serivce.model.dto.RefreshTokenResponse;
import com.biterush.auth_serivce.model.dto.UserCredentialsResponse;


public record LoginResponseDTO(UserCredentialsResponse response, String jwtToken, RefreshTokenResponse refreshTokenResponse) {
}
