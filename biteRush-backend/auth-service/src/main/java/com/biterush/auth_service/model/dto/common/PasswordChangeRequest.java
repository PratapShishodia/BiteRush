package com.biterush.auth_service.model.dto.common;

public record PasswordChangeRequest(String oldPassword,String newPassword) {
}
