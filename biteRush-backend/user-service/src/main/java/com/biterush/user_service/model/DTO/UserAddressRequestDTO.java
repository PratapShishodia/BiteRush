package com.biterush.user_service.model.DTO;

import com.biterush.user_service.model.enums.ADDRESS_TYPE;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressRequestDTO {
    private ADDRESS_TYPE label;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private float latitude;
    private float longitude;
    private boolean isDefault;
    @NotBlank(message = "User ID Required")
    private UUID userId;
}
