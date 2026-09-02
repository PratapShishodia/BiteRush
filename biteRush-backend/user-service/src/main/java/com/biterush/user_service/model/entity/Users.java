package com.biterush.user_service.model.entity;

import com.biterush.user_service.model.enums.GENDER;
import com.biterush.user_service.model.enums.USER_STATUS;
import jakarta.persistence.*;
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
@Entity
public class Users {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImage;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private GENDER gender;
    @Enumerated(EnumType.STRING)
    private USER_STATUS status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = USER_STATUS.ACTIVE;
    }

}
