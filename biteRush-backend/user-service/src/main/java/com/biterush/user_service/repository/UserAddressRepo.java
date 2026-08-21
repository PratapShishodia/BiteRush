package com.biterush.user_service.repository;

import com.biterush.user_service.model.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress,Long> {
    List<UserAddress> findByUserUserId(UUID userId);
    @Modifying
    @Query("""
    UPDATE UserAddress a
    SET a.isDefault = false
    WHERE a.user.userId = :userId
      AND a.addressId <> :addressId
""")
    void unsetDefaultAddress(
                    @Param("userId") UUID userId,
                    @Param("addressId") Long addressId
            );
}
