package com.biterush.user_service.model.DTO;

import com.biterush.user_service.model.enums.ADDRESS_TYPE;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressResponseDTO {
    private Long addressId;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
