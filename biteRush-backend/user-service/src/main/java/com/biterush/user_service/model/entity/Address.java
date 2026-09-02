package com.biterush.user_service.model.entity;

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
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;
    @Enumerated(EnumType.STRING)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isDefault = false;
    }
}
