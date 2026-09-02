package com.biterush.auth_service.repository;

import com.biterush.auth_service.model.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, UUID> {
    Optional<UserCredentials> findByEmail(String email);
    Optional<UserCredentials> findByActivationToken(String activationToken);
    Optional<UserCredentials> findByUserId(UUID userId);
    Boolean existsByEmailAndUserIdNot(String email, UUID userId);
}
