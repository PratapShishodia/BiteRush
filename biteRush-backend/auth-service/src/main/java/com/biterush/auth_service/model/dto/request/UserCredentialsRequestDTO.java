package com.biterush.auth_service.model.dto.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsRequestDTO {
    private UUID userId;
    private String email;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
}
