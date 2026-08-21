package com.biterush.user_service.model.DTO;

import com.biterush.user_service.model.enums.GENDER;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImageUrl;
    private String dateOfBirth;
    private GENDER gender;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
