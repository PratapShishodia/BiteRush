package com.biterush.user_service.model.dto.request;

import com.biterush.user_service.model.entity.Users;
import com.biterush.user_service.model.enums.ADDRESS_TYPE;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddressRequestDTO {
    private ADDRESS_TYPE addressType;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID user;
}
