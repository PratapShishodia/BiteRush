package com.biterush.user_service.model.dto.response;
import com.biterush.user_service.model.enums.GENDER;
import com.biterush.user_service.model.enums.USER_STATUS;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImage;
    private Date dateOfBirth;
    private GENDER gender;
    private USER_STATUS status;
    private List<AddressResponseDTO> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
