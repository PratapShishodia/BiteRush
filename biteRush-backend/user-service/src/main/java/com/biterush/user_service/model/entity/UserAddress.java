package com.biterush.user_service.model.entity;

import com.biterush.user_service.model.enums.ADDRESS_TYPE;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    @Enumerated(EnumType.STRING)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;
}
