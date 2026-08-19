package com.biterush.auth_serivce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
