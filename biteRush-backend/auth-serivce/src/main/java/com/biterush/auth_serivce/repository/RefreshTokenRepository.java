package com.biterush.auth_serivce.repository;

import com.biterush.auth_serivce.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(UUID userId);

    Optional<RefreshToken> findByRefreshToken(String tokenHashed);
}
