package com.biterush.menu_service.model.dto.common;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String apiPath, HttpStatusCode errorCode, String error, LocalDateTime errorDateTime) {
}
