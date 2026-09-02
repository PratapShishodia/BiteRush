package com.biterush.auth_service.model.dto.response;

import com.biterush.auth_service.model.enums.STATUS;
import lombok.*;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsResponseDTO {
    private UUID credentialsId;
    private UUID userId;
    private String email;
    private String phone;
    private String password;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private STATUS status;
}
