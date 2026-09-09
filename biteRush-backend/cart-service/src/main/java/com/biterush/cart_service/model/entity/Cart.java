package com.biterush.cart_service.model.entity;

import com.biterush.cart_service.model.enums.STATUS;
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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;
    private UUID userId;
    private UUID restaurantId;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = STATUS.ACTIVE;
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
