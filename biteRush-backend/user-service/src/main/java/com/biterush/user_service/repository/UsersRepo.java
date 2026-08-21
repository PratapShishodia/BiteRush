package com.biterush.user_service.repository;

import com.biterush.user_service.model.entity.UserAddress;
import com.biterush.user_service.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<Users, UUID> {
}
