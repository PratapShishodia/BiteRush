package com.biterush.user_service.model.entity;

import com.biterush.user_service.model.enums.GENDER;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImageUrl;
    private String dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GENDER gender;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserAddress> addressList;
}
