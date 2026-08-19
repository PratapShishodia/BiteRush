package com.biterush.auth_serivce.repository;

import com.biterush.auth_serivce.model.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByUserId(UUID userId);

    Optional<UserCredentials> findByEmail(String email);

    Optional<UserCredentials> findByActivationToken(String activationToken);

    Boolean existsByEmailAndUserIdNot(String email, UUID userId);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
